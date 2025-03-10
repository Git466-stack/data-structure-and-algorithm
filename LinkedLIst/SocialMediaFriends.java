import java.util.ArrayList;
import java.util.List;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds; // List of Friend IDs
    User next; // Pointer to the next user

    // Constructor
    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }

    // Add a friend connection
    public void addFriend(int friendId) {
        if (!friendIds.contains(friendId)) {
            friendIds.add(friendId);
        }
    }

    // Remove a friend connection
    public void removeFriend(int friendId) {
        friendIds.remove(Integer.valueOf(friendId));
    }
}

class SocialMedia {
    private User head = null;

    // Add a new user
    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
        System.out.println("User added: " + name);
    }

    // Search for a user by User ID
    public User findUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Search for a user by Name
    public User findUserByName(String name) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Add a friend connection between two users
    public void addFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            user1.addFriend(userId2);
            user2.addFriend(userId1);
            System.out.println("Friend connection added between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found!");
        }
    }

    // Remove a friend connection
    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            user1.removeFriend(userId2);
            user2.removeFriend(userId1);
            System.out.println("Friend connection removed between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found!");
        }
    }

    // Find mutual friends between two users
    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            List<Integer> mutualFriends = new ArrayList<>(user1.friendIds);
            mutualFriends.retainAll(user2.friendIds);

            System.out.println("Mutual friends between " + user1.name + " and " + user2.name + ":");
            if (mutualFriends.isEmpty()) {
                System.out.println("No mutual friends.");
            } else {
                for (int friendId : mutualFriends) {
                    User friend = findUserById(friendId);
                    System.out.println(friend.name + " (User ID: " + friendId + ")");
                }
            }
        } else {
            System.out.println("One or both users not found!");
        }
    }

    // Display all friends of a specific user
    public void displayFriends(int userId) {
        User user = findUserById(userId);

        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            if (user.friendIds.isEmpty()) {
                System.out.println("No friends added yet.");
            } else {
                for (int friendId : user.friendIds) {
                    User friend = findUserById(friendId);
                    System.out.println(friend.name + " (User ID: " + friendId + ")");
                }
            }
        } else {
            System.out.println("User not found!");
        }
    }

    // Count the number of friends for each user
    public void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }

    // Display all users
    public void displayAllUsers() {
        User temp = head;
        System.out.println("All Users:");
        while (temp != null) {
            System.out.println("[User ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age + "]");
            temp = temp.next;
        }
    }
}

public class SocialMediaFriends {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();

        // Adding users
        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 28);
        sm.addUser(3, "Charlie", 22);

        // Adding friend connections
        sm.addFriendConnection(1, 2);
        sm.addFriendConnection(1, 3);

        // Display friends
        sm.displayFriends(1);

        // Find mutual friends
        sm.findMutualFriends(2, 3);

        // Count friends for each user
        sm.countFriends();

        // Display all users
        sm.displayAllUsers();

        // Remove a friend connection
        sm.removeFriendConnection(1, 2);

        // Display friends after removal
        sm.displayFriends(1);
    }
}
