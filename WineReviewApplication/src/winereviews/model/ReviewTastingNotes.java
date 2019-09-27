package winereviews.model;

public class ReviewTastingNotes {
    protected int reviewTastingNoteId;
    protected int reviewId;
    protected String note;

    public ReviewTastingNotes(int reviewTastingNoteId) {
        this.reviewTastingNoteId = reviewTastingNoteId;
    }

    public ReviewTastingNotes(int reviewTastingNoteId, int reviewId, String note) {
        this.reviewTastingNoteId = reviewTastingNoteId;
        this.reviewId = reviewId;
        this.note = note;
    }

    public int getReviewTastingNoteId() {
        return reviewTastingNoteId;
    }

    public void setReviewTastingNoteId(int reviewTastingNoteId) {
        this.reviewTastingNoteId = reviewTastingNoteId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
