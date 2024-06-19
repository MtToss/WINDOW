package buginvaderz.window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MusicDatabaseHandler {

    public List<SpuuchifyTemplate> fetchMusicData() throws IOException {
        List<SpuuchifyTemplate> musicList = new ArrayList<>();
        String url = "jdbc:sqlite:MusicDatabase.db";
        String sql = "SELECT IMAGE_URL, TITLE, ARTIST, ALBUM, DURATION, AUDIO_PATH FROM MUSIC_DATA";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String imageUrl = rs.getString("image_url");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String album = rs.getString("album");
                String duration = rs.getString("duration");
                String audioPath = rs.getString("audio_path");

                SpuuchifyTemplate music = new SpuuchifyTemplate(imageUrl, title, artist, album, duration, audioPath, false);
                musicList.add(music);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return musicList;
    }
}
