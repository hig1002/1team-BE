package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.converter.MailConverter;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Photo;
import kwthon_1team.kwthon.repository.PhotoRepository;
import kwthon_1team.kwthon.utils.S3FileComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final S3FileComponent s3FileComponent;
    private final MailConverter mailConverter;
    private final PhotoRepository photoRepository;

    public List<Photo> createAndSavePhoto(Mail mail, List<MultipartFile> photos) {
        return photos.stream()
                .map(photo -> s3FileComponent.uploadFile("mail", photo))
                .map(photoUrl -> mailConverter.toPhoto(mail, photoUrl))
                .map(photoRepository::save)
                .toList();
    }
}
