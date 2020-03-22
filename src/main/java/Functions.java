import java.util.*;
import java.util.stream.Collectors;

// class with main methods to support program operation
public class Functions {

    //variables that store arrays of posts and users
    Post[] posts;
    User[] users;
    //HashMap which connects posts with users
    HashMap<Post, Optional<User>> postsWithUsers;

    public Functions() {
    }

    public Functions(Post[] posts, User[] users) {
        this.posts = posts;
        this.users = users;
        matchPostsWithUsers();
    }

    //method match post and users by userId and put in HashMap (key-Post, value-User)
    public HashMap<Post, Optional<User>> matchPostsWithUsers(){
        postsWithUsers = new HashMap<Post, Optional<User>>();
        for (Post post : posts) {
           Optional<User> user = Arrays.stream(users).
                    filter(p -> p.getId()==post.getUserId()).
                    findFirst();
            postsWithUsers.put(post, user);
        }
        return postsWithUsers;
    }

    // method counts how many posts users have written, return list subtitles, one subtitle to one user
    public ArrayList<String> countingUsersPosts(){
        // response - list of returned subtitles
        ArrayList<String> responses = new ArrayList<String>(Collections.nCopies(users.length, ""));
        // counters - list of counters, regarding the number of posts written for individual users
        ArrayList<Integer> counters = new ArrayList<Integer>(Collections.nCopies(users.length, 0));
        // the loop is counting  by posts the number of users entries
        for (int i = 0; i < users.length; i++) {
            for (Post post: posts) {
                if(post.getUserId()==users[i].getId())
                    counters.set(i, counters.get(i)+1);
            }
        }
        // assigning counters to the answer list (responses)
        for (int i = 0; i <users.length ; i++) {
            responses.set(i, users[i].getName()+" napisał(a) "+counters.get(i)+" postów");
        }
        return responses;
    }

    // method check unique the posts (if title of posts there is more than 1 time, that post is not unique)
    // return list titles post, which are not unique
    public List<String> checkUniquePosts(){
        // repeatedTitles - list of returned titles post, which are not unique
        ArrayList<String> repeatedTitles = new ArrayList<String>();
        for (int i = 0; i < posts.length; i++) {
            for (int j = i+1; j < posts.length; j++) {
                if(posts[i].getTitle().equals(posts[j].getTitle()))
                    repeatedTitles.add(posts[i].getTitle());
            }
        }
        // removing duplicate values from the list
        List<String> distinctRepeatedTitles = repeatedTitles.stream().distinct().collect(Collectors.toList());
        return distinctRepeatedTitles;
    }

    // method assign users to other users who are nearest to them, return list subtitles about this
    public ArrayList<String> theNearestUser(){
        // response - list of returned subtitles,    theNearestUser - user, whoes the nearest to othe user
        // theNerestAddress - the shortest distance between two users,   distance - distance between two users
        ArrayList<String> responses = new ArrayList<String>();
        User theNearestUser;
        float distance;
        float theNearestAddress;
        // check count of users
        if(users.length==1)
            responses.add("In data is only one user.");
        else {
            // double loop passing through all users and counting the distance between them and establishing the smallest distance
            for (User user : users) {
                theNearestAddress = 0;
                theNearestUser = null;
                for (int i = 0; i < users.length; i++) {
                    distance = 0;
                    if (!user.equals(users[i])) {
                        distance = countDistance(user, users[i]);
                        if (theNearestAddress == 0) {
                            theNearestAddress = distance;
                            theNearestUser = users[i];
                        } else if (theNearestAddress > distance) {
                            theNearestAddress = distance;
                            theNearestUser = users[i];
                        }
                    }
                }
                // assign a response to the returned list
                responses.add("\tNablizej uzytkownika o id " + user.getId() + " jest uzytkownik o id "
                        + theNearestUser.getId() + "  (dystans wynosi " + theNearestAddress + ")");
            }
        }
        return responses;
    }

    // method count the distance between two users and return this distance
    // using algorithm that calculates the distance between two points in a coordinate system
    public float countDistance(User userFrom, User userTo){
        float distance;
        distance = (float) Math.sqrt(
                (Math.pow(userFrom.getAddress().getGeo().getLat() - userTo.getAddress().getGeo().getLat(), 2)) +
                (Math.pow(userFrom.getAddress().getGeo().getLng() - userTo.getAddress().getGeo().getLng(), 2))  );
        return distance;
    }
}
