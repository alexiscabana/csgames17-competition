package com.mirego.cschat.viewdatas;

import com.mirego.cschat.models.Conversation;
import com.mirego.cschat.models.Message;
import com.mirego.cschat.models.User;

import java.util.ArrayList;
import java.util.List;

public class ConversationViewData {

    private final Conversation conversation;
    private final List<User> users;
    private final String currentUserId;

    public ConversationViewData(Conversation conversation, List<User> users, String currentUserId) {
        this.conversation = conversation;
        this.users = users;
        this.currentUserId = currentUserId;
    }

    public MessageViewData lastMessage() {
        return new MessageViewData(conversation.getLastMessage(), otherUserFromIds(conversation.getUsers()));
    }

    public List<MessageViewData> messages() {
        List<MessageViewData> messageViewDatas = new ArrayList<>();
        for (Message message : conversation.getMessages()) {
            messageViewDatas.add(new MessageViewData(message, userForUserId(message.getUserId())));
        }
        return messageViewDatas;
    }

    private User otherUserFromIds(List<String> userIds) {
        if (userIds != null) {
            for (String userId : userIds) {
                if (!currentUserId.equals(userId)) {
                    return userForUserId(userId);
                }
            }
        }
        return null;
    }


    private User userForUserId(String userId) {
        if (users != null) {
            for (User user : users) {
                if (user.getId().equals(userId)) {
                    return user;
                }
            }
        }
        return null;
    }

    public String id() {
        return conversation.getId();
    }
}
