import java.util.*;

/**
 * @author Müslüm agah
 * @since 23.05.2024
 */

public class HW08_20210808042 {
 
}

class User {
    private int id;
    private String username;
    private String email;
    private Set<User> followers;
    private Set<User> following;
    private Set<Post> likedPosts;
    private Map<User, Queue<Message>> messages;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.id = this.hashCode();
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
        this.likedPosts = new HashSet<>();
        this.messages = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public void message(User recipient, String content) {
        if (!messages.containsKey(recipient)) {
            messages.put(recipient, new LinkedList<>());
            recipient.messages.put(this, new LinkedList<>());
        }
        Message message = new Message(this, content);
        messages.get(recipient).offer(message);
        recipient.messages.get(this).offer(message);
        message.read(recipient);
    }

    public void read(User user) {
        Queue<Message> userMessages = messages.get(user);
        if (userMessages != null) {
            for (Message message : userMessages) {
                System.out.println(message.read(user));
            }
        }
    }

    public void follow(User user) {
        if (following.contains(user)) {
            following.remove(user);
            user.followers.remove(this);
        } else {
            following.add(user);
            user.followers.add(this);
        }
    }

    public void like(Post post) {
        if (likedPosts.contains(post)) {
            likedPosts.remove(post);
            post.likedBy(this);
        } else {
            likedPosts.add(post);
            post.likedBy(this);
        }
    }

    public Post post(String content) {
        return new Post(content);
    }

    public Comment comment(Post post, String content) {
        Comment comment = new Comment(this, content);
        post.commentBy(this, comment);
        return comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

class Message {
    private boolean seen;
    private Date dateSent;
    private String content;
    private User sender;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
        this.dateSent = new Date();
        this.seen = false;
    }

    public String read(User reader) {
        if (!sender.equals(reader)) {
            seen = true;
        }
        return "Sent at: " + dateSent + "\n" + content;
    }

    public boolean hasRead() {
        return seen;
    }
}

class Post {
    private Date datePosted;
    private String content;
    private Set<User> likes;
    private Map<User, List<Comment>> comments;

    public Post(String content) {
        this.content = content;
        this.datePosted = new Date();
        this.likes = new HashSet<>();
        this.comments = new HashMap<>();
    }

    public boolean likedBy(User user) {
        if (likes.contains(user)) {
            likes.remove(user);
            return false;
        } else {
            likes.add(user);
            return true;
        }
    }

    public boolean commentBy(User user, Comment comment) {
        if (!comments.containsKey(user)) {
            comments.put(user, new ArrayList<>());
        }
        return comments.get(user).add(comment);
    }

    public String getContent() {
        return "Posted at: " + datePosted + "\n" + content;
    }

    public Comment getComment(User user, int index) {
        List<Comment> userComments = comments.get(user);
        if (userComments != null && index >= 0 && index < userComments.size()) {
            return userComments.get(index);
        } else {
            return null;
        }
    }

    public int getCommentCount() {
        int count = 0;
        for (List<Comment> commentList : comments.values()) {
            count += commentList.size();
        }
        return count;
    }

    public int getCommentCountByUser(User user) {
        List<Comment> userComments = comments.get(user);
        return userComments != null ? userComments.size() : 0;
    }
}

class Comment extends Post {
    public Comment(User user, String content) {
        super(content);
    }
}

class SocialNetwork {
    private static Map<User, List<Post>> postsByUsers = new HashMap<>();

    public static User register(String username, String email) {
        User user = new User(username, email);
        if (!postsByUsers.containsKey(user)) {
            postsByUsers.put(user, new ArrayList<>());
            return user;
        } else {
            return null;
        }
    }

    public static Post post(User user, String content) {
        if (postsByUsers.containsKey(user)) {
            Post post = new Post(content);
            postsByUsers.get(user).add(post);
            return post;
        } else {
            return null;
        }
    }

    public static User getUser(String email) {
        int hashCode = Objects.hash(email);
        for (User user : postsByUsers.keySet()) {
            if (user.hashCode() == hashCode) {
                return user;
            }
        }
        return null;
    }

    public static Set<Post> getFeed(User user) {
        Set<Post> feed = new HashSet<>();
        for (User followedUser : user.getFollowing()) {
            feed.addAll(postsByUsers.getOrDefault(followedUser, Collections.emptyList()));
        }
        return feed;
    }

    public static Map<User, String> search(String keyword) {
        Map<User, String> result = new HashMap<>();
        for (User user : postsByUsers.keySet()) {
            if (user.getUsername().contains(keyword)) {
                result.put(user, user.getUsername());
            }
        }
        return result;
    }

    public static <K, V> Map<V, Set<K>> reverseMap(Map<K, V> map) {
        Map<V, Set<K>> reversedMap = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (!reversedMap.containsKey(value)) {
                reversedMap.put(value, new HashSet<>());
            }
            reversedMap.get(value).add(key);
        }
        return reversedMap;
    }
}