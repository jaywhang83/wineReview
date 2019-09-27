package winereviews.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.model.*;

public class ReviewTastingNotesDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static ReviewTastingNotesDao instance = null;
    protected ReviewTastingNotesDao() {
        connectionManager = new ConnectionManager();
    }
    public static ReviewTastingNotesDao getInstance() {
        if(instance == null) {
            instance = new ReviewTastingNotesDao();
        }
        return instance;
    }

    /**
     * Save the ReviewTastingNotes instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public ReviewTastingNotes create(ReviewTastingNotes reviewTastingNotes) throws SQLException {
        String insertReviewTastingNotes = "INSERT INTO ReviewTastingNotes(ReviewNoteID, ReveiwId, Note) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReviewTastingNotes);
            insertStmt.setInt(1, reviewTastingNotes.getReviewTastingNoteId());
            insertStmt.setInt(2, reviewTastingNotes.getReviewId());
            insertStmt.setString(3, reviewTastingNotes.getNote());
            insertStmt.executeUpdate();

            return reviewTastingNotes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }


    /**
     * Get the ReviewTastingNotes record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single ReviewTastingNotes instance.
     */
    public ReviewTastingNotes getReviewTastingNotesFromReviewTastingNoteId(int reviewTastingNoteId) throws SQLException {
        String selectReviewTastingNote = "SELECT * FROM ReviewTastingNotes WHERE ReviewNoteId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviewTastingNote);
            selectStmt.setInt(1, reviewTastingNoteId);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                int resultReviewNoteId = results.getInt("ReviewNoteId");
                int reviewId = results.getInt("ReviewId");
                String note = results.getString("Note");
                ReviewTastingNotes reviewTastingNote = new ReviewTastingNotes(resultReviewNoteId, reviewId, note);
                return reviewTastingNote;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<ReviewTastingNotes> getReviewTastingNotesByReviewId(int reviewId) throws SQLException {
        List<ReviewTastingNotes> reviewTastingNotes = new ArrayList<>();
        String selectReviewTastingNote = "SELECT * FROM ReviewTastingNotes WHERE ReviewId=?";
        Connection connection = null;
        PreparedStatement selectStatment = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStatment = connection.prepareStatement(selectReviewTastingNote);
            selectStatment.setInt(1, reviewId);
            results = selectStatment.executeQuery();

            while (results.next()) {
                int reviewTastingNoteId = results.getInt("ReviewNoteId");
                int resultReviewId = results.getInt("ReviewId");
                String note = results.getString("Note");
                ReviewTastingNotes reviewTastingNote = new ReviewTastingNotes(reviewTastingNoteId, resultReviewId, note);
                reviewTastingNotes.add(reviewTastingNote);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStatment != null) {
                selectStatment.close();
            }
        }
        return reviewTastingNotes;
    }


    public List<ReviewTastingNotes> getReviewTastingNotesByNote(String note) throws SQLException {
        List<ReviewTastingNotes> reviewTastingNotes = new ArrayList<>();
        String selectReviewTastingNote = "SELECT * FROM ReviewTastingNotes WHERE Note=?";
        Connection connection = null;
        PreparedStatement selectStatment = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStatment = connection.prepareStatement(selectReviewTastingNote);
            selectStatment.setString(1, note);
            results = selectStatment.executeQuery();

            while (results.next()) {
                int reviewTastingNoteId = results.getInt("ReviewNoteId");
                int reviewId = results.getInt("ReviewId");
                String resultNote = results.getString("Note");
                ReviewTastingNotes reviewTastingNote = new ReviewTastingNotes(reviewTastingNoteId, reviewId, resultNote);
                reviewTastingNotes.add(reviewTastingNote);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStatment != null) {
                selectStatment.close();
            }
        }
        return reviewTastingNotes;
    }


}
