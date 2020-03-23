import Userparameters.Address;
import Userparameters.AddressParameters.Geo;
import Userparameters.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

// using  BDD - Behaviour Driven Development
public class FunctionsTest {

    User[] users;
    Post[] posts;

    @Test
    public void should_counting_users_posts() {
        //given
        Functions functions = new Functions(posts, users);
        //when
        ArrayList<String> responses = functions.countingUsersPosts();
        //then
        Assert.assertEquals(responses.size(), 3);
        Assert.assertEquals("Jan napisał(a) 2 postów", responses.get(0));
        Assert.assertEquals("Anna napisał(a) 3 postów", responses.get(1));
        // Tom didn't write any post
        Assert.assertNotEquals("Tom napisał(a) 2 postów", responses.get(1));
    }

    @Test
    public void should_check_unique_posts() {
        //given
        Functions functions = new Functions(posts, users);
        //when
        List<String> responses = functions.checkUniquePosts();
        // changing title on the sam title from other post
        functions.posts[2].setTitle("Two");
        List<String> responses2 = functions.checkUniquePosts();
        //then
        // in this case the list is empty because all title is unique
        Assert.assertTrue(responses.size()==0);
        // in this case the list is not empty because two titles is the same
        Assert.assertTrue(responses2.size()==1);
        Assert.assertEquals("Two", responses2.get(0));
    }

    @Test
    public void should_the_nearest_user() {
        //given
        Functions functions = new Functions(posts, users);
        //when
        ArrayList<String> response = functions.theNearestUser();
        // changing users table to one element table
        functions.users = new User[]{users[0]};
        ArrayList<String> response2 = functions.theNearestUser();
        //then
        Assert.assertEquals(response.size(), 3);
        Assert.assertTrue(response.get(0).equals("	Nablizej uzytkownika o id 1 jest uzytkownik o id 2  (dystans wynosi 31.622776)"));
        Assert.assertTrue(response.get(2).equals("	Nablizej uzytkownika o id 3 jest uzytkownik o id 1  (dystans wynosi 91.24144)"));
        // in this case users table have only one element
        Assert.assertTrue(response2.get(0)=="In data is only one user.");
    }

    @Test
    public void should_count_distance() {
        //given
        Functions functions = new Functions();
        //when
        Float distance = functions.countDistance(users[0], users[1]);
        // changing geographical coordinate
        users[1].getAddress().getGeo().setLat(30);
        users[1].getAddress().getGeo().setLng(-120);
        Float distance2 = functions.countDistance(users[0], users[1]);
        //then
        Assert.assertFalse(distance==20);
        Assert.assertTrue(distance==(float)31.622776);
        Assert.assertTrue(distance2==(float)145.6022);
    }

    @Before
    public void prepareData(){
        users = new User[]{
                new User(1, "Jan", "Kot", "mail",
                        new Address("Street", "Suite", "city", "zip",
                                new Geo(-10,20)), "1234", "website" ,
                        new Company("name", "catch", "bs")),
                new User(2, "Anna", "Zyla", "mail2",
                        new Address("Street2", "Suite2", "city2", "zip2",
                                new Geo(20,10)), "4321", "website2" ,
                        new Company("name2", "catch2", "bs2")),
                new User(3, "Tom", "Kot", "mail3",
                        new Address("Street3", "Suite3", "city3", "zip3",
                                new Geo(-100,5)), "4321", "website2" ,
                        new Company("name3", "catch3", "bs3"))  };
        posts = new Post[]{
                new Post(1,1,"One","First body"),
                new Post(2,2,"Two","Second body"),
                new Post(2,3,"Three","Third two"),
                new Post(1,4,"Four","Fourth body"),
                new Post(2,5,"Five","Fifth body")};
    }
}