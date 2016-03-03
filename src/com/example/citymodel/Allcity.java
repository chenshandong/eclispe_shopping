package com.example.citymodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Table(name="recentcity")
public class Allcity extends Model implements Cloneable{

	@Column
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @Expose
    private String centerx;
    @SerializedName("district_online")
    @Expose
    private String districtOnline;
    @Expose
    private String centery;
    @Column
    @Expose
    private String name;
    @Expose
    private String province;
    @SerializedName("zone_online")
    @Expose
    private String zoneOnline;
    @Expose
    private String pinyin;

    @Override
    public Allcity clone() {
    	try {
    		Allcity allcity = (Allcity) super.clone();
			return allcity;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 
     * @return
     *     The cityId
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 
     * @param cityId
     *     The city_id
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 
     * @return
     *     The centerx
     */
    public String getCenterx() {
        return centerx;
    }

    /**
     * 
     * @param centerx
     *     The centerx
     */
    public void setCenterx(String centerx) {
        this.centerx = centerx;
    }

    /**
     * 
     * @return
     *     The districtOnline
     */
    public String getDistrictOnline() {
        return districtOnline;
    }

    /**
     * 
     * @param districtOnline
     *     The district_online
     */
    public void setDistrictOnline(String districtOnline) {
        this.districtOnline = districtOnline;
    }

    /**
     * 
     * @return
     *     The centery
     */
    public String getCentery() {
        return centery;
    }

    /**
     * 
     * @param centery
     *     The centery
     */
    public void setCentery(String centery) {
        this.centery = centery;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The province
     */
    public String getProvince() {
        return province;
    }

    /**
     * 
     * @param province
     *     The province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 
     * @return
     *     The zoneOnline
     */
    public String getZoneOnline() {
        return zoneOnline;
    }

    /**
     * 
     * @param zoneOnline
     *     The zone_online
     */
    public void setZoneOnline(String zoneOnline) {
        this.zoneOnline = zoneOnline;
    }

    /**
     * 
     * @return
     *     The pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 
     * @param pinyin
     *     The pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

}
