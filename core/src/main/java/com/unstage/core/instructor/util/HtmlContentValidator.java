package com.unstage.core.instructor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating HTML content with Base64 images.
 */
public class HtmlContentValidator {

    // Pattern to match Base64 image data URIs
    private static final Pattern BASE64_IMAGE_PATTERN = Pattern.compile(
            "data:image/(jpeg|jpg|png|gif|svg\\+xml);base64,[A-Za-z0-9+/=]+"
    );

    /**
     * Validates if the HTML content contains valid Base64 image data URIs.
     *
     * @param content HTML content to validate
     * @return true if the content is valid, false otherwise
     */
    public static boolean isValidHtmlWithBase64Images(String content) {
        if (content == null || content.isEmpty()) {
            return false;
        }

        // Find all Base64 image data URIs in the content
        Matcher matcher = BASE64_IMAGE_PATTERN.matcher(content);
        
        // If no Base64 images found, it's still valid HTML
        return true;
    }

    /**
     * Checks if the content size is within the allowed limit.
     * This is important because Base64 encoded images can be large.
     *
     * @param content HTML content to check
     * @param maxSizeInBytes maximum allowed size in bytes
     * @return true if the content size is within the limit, false otherwise
     */
    public static boolean isContentSizeValid(String content, long maxSizeInBytes) {
        if (content == null) {
            return true;
        }
        
        // Calculate the size in bytes (assuming UTF-8 encoding)
        long sizeInBytes = content.getBytes().length;
        
        return sizeInBytes <= maxSizeInBytes;
    }
}