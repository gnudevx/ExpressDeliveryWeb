package vn.iostar.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final JavaMailSender mailSender;
    public ChatWebSocketHandler(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Autowired
    private static final String ADMIN_EMAIL = "vovannam220304@gmail.com"; // Địa chỉ email của admin
    // Lưu trữ session của admin và user
    private static Map<String, WebSocketSession> sessions = new HashMap<>();
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String userMessage = message.getPayload(); // Nội dung tin nhắn
        // Nếu đây là tin nhắn đầu tiên (xác định vai trò)
        if (userMessage.startsWith("role:")) {
            if (userMessage.equals("role:admin")) {
                sessions.put("admin", session); // Lưu session của admin
                session.sendMessage(new TextMessage("Bạn đã kết nối như admin."));
            } else if (userMessage.equals("role:user")) {
                sessions.put("user", session); // Lưu session của user
                session.sendMessage(new TextMessage("Bạn đã kết nối như user."));
            }
            return;
        }
        // Xử lý tin nhắn từ user
        if (session.equals(sessions.get("user"))) {
            sendEmailToAdmin(userMessage); // Gửi email tới admin
            session.sendMessage(new TextMessage("Admin đã nhận được tin nhắn của bạn. Chờ phản hồi."));
            // Gửi tin nhắn cho admin qua WebSocket (nếu admin đang kết nối)
            WebSocketSession adminSession = sessions.get("admin");
            if (adminSession != null) {
                adminSession.sendMessage(new TextMessage("Tin nhắn từ user: " + userMessage));
            }
        }
        // Xử lý tin nhắn từ admin
        else if (session.equals(sessions.get("admin"))) {
            WebSocketSession userSession = sessions.get("user");
            if (userSession != null) {
                userSession.sendMessage(new TextMessage("Admin: " + userMessage));
            }
        }
    }
    private void sendEmailToAdmin(String customerMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(ADMIN_EMAIL);
        mailMessage.setSubject("Tin nhắn mới từ khách hàng");
        mailMessage.setText("Khách hàng đã gửi tin nhắn: " + customerMessage);

        mailSender.send(mailMessage);
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("Vui lòng gửi vai trò của bạn bằng cách gửi 'role:user' hoặc 'role:admin'."));
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.values().remove(session);
    }
}

