package no.niths.application.rest.helper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import no.niths.application.rest.exception.BadRequestException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * This implementation makes up for the flaws in Apache's implementation used by
 * Spring. This implementation however handles exceptions that otherwise would
 * be impossible to catch.
 *
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {

    @Override
    @SuppressWarnings("unchecked")
    protected MultipartParsingResult parseRequest(
            final HttpServletRequest req) {

        String encoding = determineEncoding(req);
        FileUpload fileUpload = prepareFileUpload(encoding);

        List<FileItem> fileItems;

        try {
            fileItems = ((ServletFileUpload) fileUpload).parseRequest(req);
        } catch (FileUploadBase.SizeLimitExceededException e) {
            throw new BadRequestException("Multipart data size too large");
        } catch (FileUploadException e) {
            throw new BadRequestException("No multipart data present");
        }

        return parseFileItems(fileItems, encoding);
    }
}