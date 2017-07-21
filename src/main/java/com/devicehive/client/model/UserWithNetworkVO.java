/*
 * Device Hive REST API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 3.3.0-SNAPSHOT
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.devicehive.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserWithNetworkVO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-20T15:03:42.016+03:00")
public class UserWithNetworkVO {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("login")
  private String login = null;

  /**
   * Gets or Sets role
   */
  @JsonAdapter(RoleEnum.Adapter.class)
  public enum RoleEnum {
    NUMBER_0(0),
    
    NUMBER_1(1);

    private Integer value;

    RoleEnum(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static RoleEnum fromValue(String text) {
      for (RoleEnum b : RoleEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<RoleEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final RoleEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public RoleEnum read(final JsonReader jsonReader) throws IOException {
        Integer value = jsonReader.nextInt();
        return RoleEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("role")
  private RoleEnum role = null;

  /**
   * Gets or Sets status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    NUMBER_0(0),
    
    NUMBER_1(1),
    
    NUMBER_2(2);

    private Integer value;

    StatusEnum(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        Integer value = jsonReader.nextInt();
        return StatusEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("status")
  private StatusEnum status = null;

  @SerializedName("lastLogin")
  private DateTime lastLogin = null;

  @SerializedName("data")
  private JsonStringWrapper data = null;

  @SerializedName("introReviewed")
  private Boolean introReviewed = false;

  @SerializedName("networks")
  private List<NetworkVO> networks = null;

  public UserWithNetworkVO id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserWithNetworkVO login(String login) {
    this.login = login;
    return this;
  }

   /**
   * Get login
   * @return login
  **/
  @ApiModelProperty(required = true, value = "")
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public UserWithNetworkVO role(RoleEnum role) {
    this.role = role;
    return this;
  }

   /**
   * Get role
   * @return role
  **/
  @ApiModelProperty(value = "")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public UserWithNetworkVO status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public UserWithNetworkVO lastLogin(DateTime lastLogin) {
    this.lastLogin = lastLogin;
    return this;
  }

   /**
   * Get lastLogin
   * @return lastLogin
  **/
  @ApiModelProperty(value = "")
  public DateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(DateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  public UserWithNetworkVO data(JsonStringWrapper data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public JsonStringWrapper getData() {
    return data;
  }

  public void setData(JsonStringWrapper data) {
    this.data = data;
  }

  public UserWithNetworkVO introReviewed(Boolean introReviewed) {
    this.introReviewed = introReviewed;
    return this;
  }

   /**
   * Get introReviewed
   * @return introReviewed
  **/
  @ApiModelProperty(value = "")
  public Boolean getIntroReviewed() {
    return introReviewed;
  }

  public void setIntroReviewed(Boolean introReviewed) {
    this.introReviewed = introReviewed;
  }

  public UserWithNetworkVO networks(List<NetworkVO> networks) {
    this.networks = networks;
    return this;
  }

  public UserWithNetworkVO addNetworksItem(NetworkVO networksItem) {
    if (this.networks == null) {
      this.networks = new ArrayList<NetworkVO>();
    }
    this.networks.add(networksItem);
    return this;
  }

   /**
   * Get networks
   * @return networks
  **/
  @ApiModelProperty(value = "")
  public List<NetworkVO> getNetworks() {
    return networks;
  }

  public void setNetworks(List<NetworkVO> networks) {
    this.networks = networks;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserWithNetworkVO userWithNetworkVO = (UserWithNetworkVO) o;
    return Objects.equals(this.id, userWithNetworkVO.id) &&
        Objects.equals(this.login, userWithNetworkVO.login) &&
        Objects.equals(this.role, userWithNetworkVO.role) &&
        Objects.equals(this.status, userWithNetworkVO.status) &&
        Objects.equals(this.lastLogin, userWithNetworkVO.lastLogin) &&
        Objects.equals(this.data, userWithNetworkVO.data) &&
        Objects.equals(this.introReviewed, userWithNetworkVO.introReviewed) &&
        Objects.equals(this.networks, userWithNetworkVO.networks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login, role, status, lastLogin, data, introReviewed, networks);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserWithNetworkVO {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    lastLogin: ").append(toIndentedString(lastLogin)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    introReviewed: ").append(toIndentedString(introReviewed)).append("\n");
    sb.append("    networks: ").append(toIndentedString(networks)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

