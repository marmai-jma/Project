package com.bnpparibas.bddf.projet.media.exposition;

import com.bnpparibas.bddf.projet.media.application.MediaService;
import com.bnpparibas.bddf.projet.media.domain.Category;
import com.bnpparibas.bddf.projet.media.domain.MediaNotation;
import com.bnpparibas.bddf.projet.media.domain.Review;
import com.bnpparibas.bddf.projet.media.exposition.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MediaResource {

    @Autowired
    private MediaService mediaService;

    @RequestMapping(method= RequestMethod.GET, path={"/medias"})
    public List<MediaLightDTO> listAllMedias(){
        return MediaAdapter.adaptToMediaLightDTOList(this.mediaService.listAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/medias/category/{category}"})
    public List<MediaLightDTO> listMediaByCategory(@PathVariable("category") Category category) {
        return MediaAdapter.adaptToMediaLightDTOList(this.mediaService.listByCategory(category));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/medias/{mediaId}"})
    public MediaDTO detailMedia(@PathVariable("mediaId") String mediaId ){
       return MediaAdapter.adaptToMediaDTO(this.mediaService.obtain(mediaId));
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/medias"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createMedia(@Valid @RequestBody MediaDTO mediaDTO) {
        this.mediaService.create(MediaAdapter.transformToMedia(mediaDTO));
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/medias/{mediaId}"})
    public void updateMedia(@PathVariable("mediaId") String mediaId, @RequestBody MediaDTO mediaDTO) {
        this.mediaService.update(mediaId, MediaAdapter.transformToMedia(mediaDTO));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/medias/{mediaId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMedia(@PathVariable("mediaId") String mediaId) {
        this.mediaService.remove(mediaId);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/medias/{mediaId}/notation/{userLogin}"})
    public void addNotationToMedia(@PathVariable("mediaId") String mediaId, @RequestBody MediaNotationFormDTO mediaNotationFormDTO, @PathVariable("userLogin") String userLogin) {
        this.mediaService.addNotationToMedia(mediaId, mediaNotationFormDTO.isLiked(), userLogin);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/medias/{mediaId}/notation/{userLogin}"})
    public MediaNotationLightDTO getMediaNotationByMediaIdUserLogin(@PathVariable("mediaId") String mediaId, @PathVariable("userLogin") String userLogin ){
        MediaNotation mediaNotation = this.mediaService.getNotationByMediaUser(mediaId, userLogin);
        return (mediaNotation != null) ? MediaNotationAdapter.adaptToMediaNotationDTO(mediaNotation): null;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/medias/{mediaId}/review/{userLogin}"})
    public ReviewDTO getReviewByMediaIdUserLogin(@PathVariable("mediaId") String mediaId, @PathVariable("userLogin") String userLogin ){
        Review review = this.mediaService.getReviewByMediaUser(mediaId, userLogin);
        return (review != null) ? ReviewAdapter.adaptToReviewDTO(review): null;
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/medias/{mediaId}/review/{userLogin}"})
    public ReviewDTO addReview(@PathVariable("mediaId") String mediaId, @RequestBody ReviewFormDTO reviewFormDTO, @PathVariable("userLogin") String userLogin) {
        return ReviewAdapter.adaptToReviewDTO(this.mediaService.addReviewToMedia(mediaId, reviewFormDTO.getComment(), userLogin));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/medias/{mediaId}/review/{userLogin}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeReviewByMediaUser(@PathVariable("mediaId") String mediaId, @PathVariable("userLogin") String userLogin) {
        this.mediaService.removeReviewFromMediaByUser(mediaId, userLogin);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/medias/{mediaId}/reviews"})
    public List<ReviewDTO> listAllReviewsByMedia(@PathVariable("mediaId") String mediaId ){
        return ReviewAdapter.adaptToReviewDTOList(this.mediaService.listAllReviewsByMedia(mediaId));
    }

    @RequestMapping(method= RequestMethod.GET, path={"/recommendations"})
    public List<MediaRecoLightDTO> listAllRecommendations(){
        return (MediaAdapter.adaptToMediaRecoLightDTOList(this.mediaService.listAllMediaRecoLight()));
    }
}
