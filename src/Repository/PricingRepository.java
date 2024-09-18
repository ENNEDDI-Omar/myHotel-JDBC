package Repository;

import Database.DbConnection;
import Enums.RoomType;
import Enums.SeasonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PricingRepository {

    public int

    getPriceByRoomTypeAndSeason(RoomType roomType, SeasonType season) throws SQLException {
        String query = "SELECT price FROM pricing WHERE room_type = ? AND season = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, roomType.name());
            stmt.setString(2, season.name());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("price");
            } else {
                throw new SQLException("Prix non trouvé pour le type de chambre " + roomType + " et la saison " + season);
            }
        }
    }
}
