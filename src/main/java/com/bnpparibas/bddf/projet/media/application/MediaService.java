package com.bnpparibas.bddf.projet.media.application;

import com.bnpparibas.bddf.projet.media.domain.*;
import com.bnpparibas.bddf.projet.media.exposition.dto.MediaRecoLightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private MediaNotationRepository mediaNotationRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;


    public String create(Media newMedia){
        return this.mediaRepository.save(newMedia); }

    public Media obtain(String id){ return this.mediaRepository.get(id); }

    public List<Media> listAll(){ return this.mediaRepository.findAll(); }

    public List<Media> listByCategory(Category category){ return this.mediaRepository.findByCategory(category); }

    public void update(String id, Media mediaToUpdate){
        Media media = obtain(id);
        media.update(mediaToUpdate);
        this.mediaRepository.update(media);
    }

    public void remove(String id){
        obtain(id);
        this.mediaRepository.delete(id);
    }

    public void addNotationToMedia(String mediaId, Boolean liked, String userLogin){
        this.mediaNotationRepository.saveOrUpdate(mediaId, liked, userRepository.findByLogin(userLogin).getId());
    }

    public MediaNotation getNotationByMediaUser(String mediaId, String userLogin){
        return this.mediaNotationRepository.getByMediaIdUserId(mediaId, userRepository.findByLogin(userLogin).getId());
    }

    public Review getReviewByMediaUser(String mediaId, String userLogin){
        return this.reviewRepository.getReviewByMediaIdUserId(mediaId, userRepository.findByLogin(userLogin).getId());
    }

    public Review addReviewToMedia(String mediaId, String comment, String userLogin){
        return this.reviewRepository.saveOrUpdate(mediaId, comment, userRepository.findByLogin(userLogin).getId());
    }

    public void removeReviewFromMediaByUser(String mediaId, String userLogin){
        this.reviewRepository.removeViaMediaByUser(mediaId, userRepository.findByLogin(userLogin).getId());
    }
    public List<Review> listAllReviewsByMedia(String mediaId){
        return this.reviewRepository.searchByMediaId(mediaId);
    }

    public List<Media> listAllMediaRecoLight (){
        return mediaRepository.findAllMediaWithNotation();
    }
}
