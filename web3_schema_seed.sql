
/* =============================================================
							Logistics DB
   ============================================================= */
---------------------------------------------------------------
-- 0) Create / select database
---------------------------------------------------------------
CREATE DATABASE ExpressTransportDB;
USE ExpressTransportDB;
GO
---------------------------------------------------------------
-- 1) Master data & users
---------------------------------------------------------------

-- 1.1 Address — shared by Users / Recipients / PostOffices
CREATE TABLE dbo.Address (
    AddressId   INT IDENTITY(1,1) PRIMARY KEY,
    City        NVARCHAR(100) NOT NULL,
    District    NVARCHAR(100) NOT NULL,
    Ward        NVARCHAR(100) NOT NULL,
    Detail      NVARCHAR(200) NOT NULL
);

-- 1.2 Roles — system roles for use! 
CREATE TABLE dbo.Roles (
    RoleId   INT IDENTITY(1,1) PRIMARY KEY,
    RoleName NVARCHAR(50) NOT NULL UNIQUE -- CUSTOMER, SHIPPER, STAFF, ADMIN
);

-- 1.3 Users — includes shipper, staff, customer: created pacel for order! 
CREATE TABLE dbo.Users (
    UserId        INT IDENTITY(1,1) PRIMARY KEY,
    Username      NVARCHAR(50) UNIQUE NULL,    -- optional login id
    FullName      NVARCHAR(100) NOT NULL,
    Email         NVARCHAR(100) UNIQUE NULL,
    Phone         NVARCHAR(20)  NULL,          -- NVARCHAR to support +84, leading zeros
    PasswordHash  NVARCHAR(255) NOT NULL,
    RoleId        INT NOT NULL,
    AddressId     INT NULL,
    Status        NVARCHAR(20) NOT NULL DEFAULT 'Active' CHECK (Status IN ('Active','Inactive')),
    CreateDate    DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Users_Roles    FOREIGN KEY (RoleId)   REFERENCES dbo.Roles(RoleId),
    CONSTRAINT FK_Users_Address  FOREIGN KEY (AddressId) REFERENCES dbo.Address(AddressId)
);

-- 1.4 PostOffices — branches/hubs -> In the city will have office receive order customer!
CREATE TABLE dbo.PostOffices (
    OfficeId  INT IDENTITY(1,1) PRIMARY KEY,
    Code      NVARCHAR(20) NOT NULL UNIQUE, -- office code
    Name      NVARCHAR(100) NOT NULL,
    AddressId INT NOT NULL,
    IsActive  BIT NOT NULL DEFAULT 1,
    CONSTRAINT FK_PostOffices_Address FOREIGN KEY (AddressId) REFERENCES dbo.Address(AddressId)
);

-- 1.5 UserOffices — map staff/shipper to an office
CREATE TABLE dbo.UserOffices (
    UserId   INT NOT NULL,
    OfficeId INT NOT NULL,
    IsPrimary BIT NOT NULL DEFAULT 1,
    FromDate DATETIME NOT NULL DEFAULT GETDATE(),
    ToDate   DATETIME NULL,
    PRIMARY KEY (UserId, OfficeId, FromDate),
    CONSTRAINT FK_UserOffices_User   FOREIGN KEY (UserId)   REFERENCES dbo.Users(UserId),
    CONSTRAINT FK_UserOffices_Office FOREIGN KEY (OfficeId) REFERENCES dbo.PostOffices(OfficeId)
);

-- 1.6 Vehicle types & vehicles
CREATE TABLE dbo.VehicleTypes (
    VehicleTypeId INT IDENTITY(1,1) PRIMARY KEY,
    Name          NVARCHAR(50) NOT NULL UNIQUE -- Motorbike, Truck, Van...
);

CREATE TABLE dbo.Vehicles (
    VehicleId     INT IDENTITY(1,1) PRIMARY KEY,
    LicensePlate  NVARCHAR(20) NOT NULL UNIQUE,
    VehicleTypeId INT NOT NULL,
    UserId        INT NULL,                 -- shipper owner if personal vehicle
    Status        NVARCHAR(20) NOT NULL DEFAULT 'Active' CHECK (Status IN ('Active','Maintenance','Inactive')),
    CONSTRAINT FK_Vehicles_Type FOREIGN KEY (VehicleTypeId) REFERENCES dbo.VehicleTypes(VehicleTypeId),
    CONSTRAINT FK_Vehicles_User FOREIGN KEY (UserId) REFERENCES dbo.Users(UserId)
);

-- 1.7 Recipients — recipient address book (often created by customer)
CREATE TABLE dbo.Recipients (
    RecipientId INT IDENTITY(1,1) PRIMARY KEY,
    FullName    NVARCHAR(100) NOT NULL,
    Phone       NVARCHAR(20)  NOT NULL,
    Email       NVARCHAR(100) NULL,
    AddressId   INT NOT NULL,
    CreatedByUserId INT NULL, -- the sender who created this contact
    CONSTRAINT FK_Recipients_Address FOREIGN KEY (AddressId) REFERENCES dbo.Address(AddressId),
    CONSTRAINT FK_Recipients_Creator FOREIGN KEY (CreatedByUserId) REFERENCES dbo.Users(UserId)
);

---------------------------------------------------------------
-- 2) Parcels, promotions, assignments, tracking, payments
---------------------------------------------------------------

-- 2.1 Promotions - each customer when created delivery pacel will apply promotion  
CREATE TABLE dbo.Promotions (
    PromotionId INT IDENTITY(1,1) PRIMARY KEY,
    Code        NVARCHAR(50) NOT NULL UNIQUE,
    Description NVARCHAR(200) NULL,
    StartDate   DATETIME NOT NULL,
    EndDate     DATETIME NULL,
    Quantity    INT NOT NULL,        -- remaining uses
    Percentage  INT NOT NULL CHECK (Percentage BETWEEN 0 AND 100)
);

-- 2.2 Parcels — shipping orders (tracking number = TrackingCode)
CREATE TABLE dbo.Parcels (
    ParcelId            INT IDENTITY(1,1) PRIMARY KEY,
    TrackingCode        NVARCHAR(50) NOT NULL UNIQUE,
    ServiceType         NVARCHAR(20) NOT NULL DEFAULT 'Standard' CHECK (ServiceType IN ('Standard','Express','SameDay')),
    UserId              INT NOT NULL, -- sender
    RecipientId         INT NOT NULL,
    OriginOfficeId      INT NOT NULL,
    DestinationOfficeId INT NOT NULL,
    CreateDate          DATETIME NOT NULL DEFAULT GETDATE(),
    CompleteDate        DATETIME NULL,
    LengthCm            DECIMAL(10,2) NULL,
    WidthCm             DECIMAL(10,2) NULL,
    HeightCm            DECIMAL(10,2) NULL,
    WeightKg            DECIMAL(10,3) NULL,
    DeclaredValue       DECIMAL(18,2) NULL,  -- declared/insurance value
    CODAmount           DECIMAL(18,2) NULL, 
	Status NVARCHAR(20) NOT NULL DEFAULT 'Pending' CHECK (Status IN ('Pending','PickedUp','InTransit','Delivered','Cancelled')),
	PromotionId INT NULL,
    Note NVARCHAR(200) NULL,
    CONSTRAINT FK_Parcels_User FOREIGN KEY (UserId) REFERENCES dbo.Users(UserId),
    CONSTRAINT FK_Parcels_Recipient FOREIGN KEY (RecipientId) REFERENCES dbo.Recipients(RecipientId),
    CONSTRAINT FK_Parcels_OriginOffice FOREIGN KEY (OriginOfficeId) REFERENCES dbo.PostOffices(OfficeId),
    CONSTRAINT FK_Parcels_DestOffice FOREIGN KEY (DestinationOfficeId) REFERENCES dbo.PostOffices(OfficeId),
    CONSTRAINT FK_Parcels_Promotion FOREIGN KEY (PromotionId) REFERENCES dbo.Promotions(PromotionId)
)
-- 2.3 History status Parcel
CREATE TABLE ParcelStatusHistory (
    StatusHistoryId INT IDENTITY(1,1) PRIMARY KEY,
    ParcelId INT NOT NULL,
    Status NVARCHAR(20) NOT NULL CHECK (Status IN ('Pending','PickedUp','InTransit','Delivered','Failed','Cancelled')),
    UpdateDate DATETIME NOT NULL DEFAULT GETDATE(),
    UpdatedByUserId INT NULL,
    Note NVARCHAR(200) NULL,
    CONSTRAINT FK_ParcelStatus_Parcel FOREIGN KEY (ParcelId) REFERENCES Parcels(ParcelId),
    CONSTRAINT FK_ParcelStatus_User FOREIGN KEY (UpdatedByUserId) REFERENCES Users(UserId)
);

--2.4 assignment Shipper 
CREATE TABLE ParcelAssignments (
    AssignmentId INT IDENTITY(1,1) PRIMARY KEY,
    ParcelId INT NOT NULL,
    ShipperId INT NOT NULL,
    OfficeId INT NOT NULL, -- chi nhánh giao
    AssignDate DATETIME NOT NULL DEFAULT GETDATE(),
    Completed BIT NOT NULL DEFAULT 0,
    CONSTRAINT FK_Assignment_Parcel FOREIGN KEY (ParcelId) REFERENCES Parcels(ParcelId),
    CONSTRAINT FK_Assignment_Shipper FOREIGN KEY (ShipperId) REFERENCES Users(UserId),
    CONSTRAINT FK_Assignment_Office FOREIGN KEY (OfficeId) REFERENCES PostOffices(OfficeId)
);