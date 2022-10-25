package space.harbour.utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import space.harbour.domain.Friend;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<Friend> readFriendsFile(String filePath) throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(filePath);
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        List<Friend> FriendList = new ArrayList<>(List.of(gson.fromJson(reader, Friend[].class)));
        reader.close();

        return FriendList;
    }

    public static void writeFriendsFile(List<Friend> FriendList, String filePath) throws FileNotFoundException, IOException {
        FileWriter writer = new FileWriter(filePath);
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        gson.toJson(FriendList, writer);
        writer.close();
    }
}
