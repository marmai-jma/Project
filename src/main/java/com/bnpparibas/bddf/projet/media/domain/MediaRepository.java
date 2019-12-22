package com.bnpparibas.bddf.projet.media.domain;

import com.bnpparibas.bddf.projet.media.domain.Media;

import java.util.List;

public interface MediaRepository {
    String save(Media media);
    Media get(String id);
    List<Media> findAll();
    void delete(String id);
}