import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

// using  BDD - Behaviour Driven Development
public class ReaderTest {
    String jsonTestPosts;
    String jsonTestUsers;

    @Test
    public void should_read_url() throws Exception {
        //given
        Reader reader = new Reader();
        String expectedMessageError = "no protocol: wrong_url_address";
        //when
        String urlText = reader.readUrl("https://jsonplaceholder.typicode.com/posts");
        Exception exception = Assert.assertThrows(java.io.IOException.class, ()->{
            String urlText3 = reader.readUrl("wrong_url_address");
        });
        String actualMessage = exception.getMessage();
        //then
        // * the url address entered above contains exactly 26919 characters
        Assert.assertEquals(urlText.length(), 26919);
        // check the error message from an incorrectly url address
        Assert.assertTrue(actualMessage.contains(expectedMessageError));
    }

    @Test
    public void should_read_posts() throws Exception {
        //given
        Reader reader = mock(Reader.class);
        given(reader.readUrl(Mockito.anyString())).willReturn(jsonTestPosts);
        //when
        String jsonPosts = reader.readUrl("testUrlPost");
        Post[] posts = new Gson().fromJson(jsonPosts, Post[].class);
        //then
        Assert.assertEquals(posts.length, 2);
        Assert.assertNotNull(posts[1].getTitle());
        Assert.assertNotNull(posts[1].getUserId());
        Assert.assertNotNull(posts[1].getBody());
        Assert.assertNotNull(posts[1].getId());
    }

    @Test
    public void should_read_Users() throws Exception {
        //given
        Reader reader = mock(Reader.class);
        given(reader.readUrl(Mockito.anyString())).willReturn(jsonTestUsers);
        //when
        String jsonUsers = reader.readUrl("testUrlPost");
        User[] users = new Gson().fromJson(jsonUsers, User[].class);
        //then
        Assert.assertEquals(users.length, 1);
        Assert.assertNotNull(users[0].getName());
        Assert.assertNotNull(users[0].getAddress());
        Assert.assertNotNull(users[0].getId());
        Assert.assertNotNull(users[0].getCompany());
        Assert.assertNotNull(users[0].getEmail());
        Assert.assertNotNull(users[0].getUsername());
        Assert.assertNotNull(users[0].getWebsite());
    }

    @Before
    public void prepareData(){
        jsonTestPosts = "[\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"qui est esse\",\n" +
                "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores.\"\n" +
                "  }\n" +
                "]";
        jsonTestUsers = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Leanne Graham\",\n" +
                "    \"username\": \"Bret\",\n" +
                "    \"email\": \"Sincere@april.biz\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Kulas Light\",\n" +
                "      \"suite\": \"Apt. 556\",\n" +
                "      \"city\": \"Gwenborough\",\n" +
                "      \"zipcode\": \"92998-3874\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-37.3159\",\n" +
                "        \"lng\": \"81.1496\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Romaguera-Crona\",\n" +
                "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "      \"bs\": \"harness real-time e-markets\"\n" +
                "    }\n" +
                "  }\n" +
                "]";
    }
}