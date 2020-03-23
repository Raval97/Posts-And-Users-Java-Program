import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        //reading the data from url addresses
        Reader reader = new Reader();
        Post[] posts = reader.readPosts("https://jsonplaceholder.typicode.com/posts");;
        User[] users = reader.readUsers("https://jsonplaceholder.typicode.com/users");;

        //creating an object to perform the main functions of the program
        Functions functions = new Functions(posts, users);

        // ancillary variables during program operation
        Scanner scanner = new Scanner(System.in);
        boolean endTheProgram = false;
        int programUserOptionSelected=0;
        ArrayList<String> returnedMethodResponses;

        System.out.println("WELCOME IN MY PROGRAM");
        while (endTheProgram == false) {

            System.out.println("\n"+new String(new char[40]).replace("\0", "#"));
            System.out.println(new String(new char[40]).replace("\0", "#"));
            System.out.println("MENU:");
            System.out.println("1. Policz liczbe postów urzydkownikom");
            System.out.println("2. Sprawdź czy tytuły postów są unikalne");
            System.out.println("3. Dlaw wszystkich użytkowników znajdż im nabliższych innych użytkowników");
            System.out.println("0. Wyjdź z programu");
            System.out.print("Podaj opcje: ");
            programUserOptionSelected = scanner.nextInt();

            switch (programUserOptionSelected) {
                case 1:
                    System.out.println("\t"+new String(new char[40]).replace("\0", "#"));
                    returnedMethodResponses = functions.countingUsersPosts();
                    for (String response :  returnedMethodResponses) {
                        System.out.println("\t"+response);
                    }
                    break;
                case 2:
                    System.out.println("\t"+new String(new char[40]).replace("\0", "#"));
                    List<String> repeatedTitles = functions.checkUniquePosts();
                    if (repeatedTitles.isEmpty())
                        System.out.println("\tWszystkie tytuły stron są unikalne");
                    else {
                        System.out.println("\tNie wszystkie tytuły stron są unikalne, poniżej ich lista:");
                        for (String title : repeatedTitles) {
                            System.out.println("\t-  "+title);
                        }
                    }
                    break;
                case 3:
                    System.out.println("\t"+new String(new char[40]).replace("\0", "#"));
                    System.out.println("\t*Dystans w linii prostej podany w jednostkach współrzędnych geograficznych");
                    returnedMethodResponses = functions.theNearestUser();
                    for (String response :  returnedMethodResponses) {
                        System.out.println(response);
                    }
                    break;
                case 0:
                    endTheProgram = true;
                    break;
                default:
                    System.out.println("\tBłędna opcja");
            }
        }
    }
}