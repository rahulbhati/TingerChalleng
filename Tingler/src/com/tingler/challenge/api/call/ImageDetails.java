package com.tingler.challenge.api.call;

public class ImageDetails {
	public String bookName;
	//public String thumbnailURL;
	public String galleryURL ;
	
	
	public String bookID,bookISBN,bookAuthor,bookDescription,bookThumbnail,bookDownLoadLink,eBookDownloadLink;
	
	
	public ImageDetails(){
		
	}
	
	public ImageDetails(String galleryURL, String bookName) {
		
		this.galleryURL = galleryURL;
		this.bookName = bookName;
	//	this.thumbnailURL = thumbnailURL;
	}
	public String getGalleryURL() {
		return galleryURL;
	}
	public void setGalleryURL(String galleryURL) {
		this.galleryURL = galleryURL;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getBookThumbnail() {
		return bookThumbnail;
	}

	public void setBookThumbnail(String bookThumbnail) {
		this.bookThumbnail = bookThumbnail;
	}

	public String getBookDownLoadLink() {
		return bookDownLoadLink;
	}

	public void setBookDownLoadLink(String bookDownLoadLink) {
		this.bookDownLoadLink = bookDownLoadLink;
	}

	public String geteBookDownloadLink() {
		return eBookDownloadLink;
	}

	public void seteBookDownloadLink(String eBookDownloadLink) {
		this.eBookDownloadLink = eBookDownloadLink;
	}
	

}
