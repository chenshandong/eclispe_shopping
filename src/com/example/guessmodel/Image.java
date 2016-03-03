package com.example.guessmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import android.os.Parcel;
import android.os.Parcelable;

@Table(name = "Image")
public class Image extends Model implements Parcelable {

	@Column
    @Expose
    private Integer width;
	@Column
    @Expose
    private String image;
	
	@Column
    @Expose
	private String product;
	
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Image() {
	}

    public int describeContents() {
        return 0;
    }


    public static final Image.Creator<Image> CREATOR
            = new Image.Creator<Image>() {
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    
    private Image(Parcel in) {
    	width = in.readInt();
    	image = in.readString();
    }

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(width);
		out.writeString(image);
	}
    
    /**
     * 
     * @return
     *     The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
