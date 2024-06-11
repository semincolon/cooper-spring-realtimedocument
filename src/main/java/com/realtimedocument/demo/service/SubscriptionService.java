package com.realtimedocument.demo.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubscriptionService {
    // 구독 경로에 대한 아이디 저장
    private final Map<String, Set<String>> subscriptions = new HashMap<>();

    // 새로운 문서를 선택할 때 이 메서드가 호출
    public void addSubscription(String documentPath, String userEmail) {
        subscriptions.computeIfAbsent(documentPath, k -> new HashSet<>()).add(userEmail);
        System.out.println("**구독** >> " + subscriptions);
    }

    // 다른 문서를 선택하거나 페이지를 닫을 때 이 메서드가 호출
    public void removeSubscription(String documentPath, String userEmail) {
        Set<String> userEmails = subscriptions.get(documentPath);
        if (userEmails != null) {
            userEmails.remove(userEmail);
            System.out.println("**구독해제** >> " + subscriptions);
            if (userEmails.isEmpty()) {
                subscriptions.remove(documentPath);
            }
        }
    }

    public Set<String> getSubscribers(String documentPath) {
        System.out.println(subscriptions);
        return subscriptions.getOrDefault(documentPath, new HashSet<>());
    }

    // 연결이 끊어질 때 모든 구독 제거
    public void removeAllSubscriptions(String userEmail) {
        for (Set<String> subscribers : subscriptions.values()) {
            subscribers.remove(userEmail);
        }
        subscriptions.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
}
