package com.realtimedocument.demo;

import com.realtimedocument.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionInterceptor implements ChannelInterceptor {
    private final SubscriptionService subscriptionService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        // STOMP 메시지를 래핑하여 헤더에 접근할 수 있게 함
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        String sessionId = accessor.getSessionId();
        String userEmail = accessor.getFirstNativeHeader("userEmail");

        // STOMP 명령어에 따라 다른 작업 수행
        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            String destination = accessor.getDestination();
            // 문서에 대한 경로일 경우 구독 정보 추가
            if (destination != null && destination.matches("/sub/workspace/[0-9a-fA-F-]{36}/subscribers")) {
                subscriptionService.addSubscription(destination, userEmail);
            }
        } else if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) {
            // 커스텀 헤더에서 destination 읽기
            String destination = accessor.getFirstNativeHeader("destination");
            // 구독 해제 또는 연결 해제
            if (destination != null && destination.matches("/sub/workspace/[0-9a-fA-F-]{36}/subscribers")) {
                subscriptionService.removeSubscription(destination, userEmail);
            }
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            subscriptionService.removeAllSubscriptions(userEmail);
        }

        // 메시지를 채널로 전달
        return message;
    }
}
