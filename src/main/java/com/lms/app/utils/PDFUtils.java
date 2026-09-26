package com.lms.app.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class PDFUtils {

    public static byte[] generateThumbnail(Path pdfPath) throws IOException {

        try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {
            var renderer = new PDFRenderer(document);
            BufferedImage pageImage = renderer.renderImageWithDPI(0, 100);
            int thumbnailWidth = 300;
            int thumbnailHeight = (pageImage.getHeight() * thumbnailWidth) / pageImage.getWidth();

            BufferedImage thumbnail = new BufferedImage(
                    thumbnailWidth,
                    thumbnailHeight,
                    BufferedImage.TYPE_INT_RGB
            );

            Graphics2D graphics = thumbnail.createGraphics();

            try {
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics.drawImage(pageImage, 0, 0, thumbnailWidth, thumbnailHeight, null);
            } finally {
                graphics.dispose();
            }
            var output = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, "jpg", output);
            return output.toByteArray();
        }
    }
}
