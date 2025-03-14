package com.bnpparibas.bddf.projet.media.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @JsonProperty
    String login;

    @JsonProperty
    String password;

    @JsonProperty
    String userName;

    @JsonProperty
    String userSurname;

    @JsonProperty
    String avatarImageURL;

    @JsonProperty
    String email;

    @JsonProperty
    boolean active;

    @JsonProperty
    Set<MediaNotationDTOMedia> mediaNotationsDTO;

    public UserDTO() {}

    public UserDTO(Long id, String login, String password, String userName, String userSurname, String avatarImageURL, String email, boolean active, Set<MediaNotationDTOMedia> mediaNotationsDTO) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.avatarImageURL = avatarImageURL;
        this.email = email;
        this.active = active;
        this.mediaNotationsDTO = mediaNotationsDTO;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getAvatarImageURL() {
        return avatarImageURL;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public Set<MediaNotationDTOMedia> getMediaNotationsDTO() {
        return mediaNotationsDTO;
    }
}
