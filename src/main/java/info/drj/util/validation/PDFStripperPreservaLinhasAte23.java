package info.drj.util.validation;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

/**
 *
 * @author dirceurjunior
 */
public class PDFStripperPreservaLinhasAte23 extends PDFTextStripper {

    private float lastY = -1;
    private int lineCount = 0;

    public PDFStripperPreservaLinhasAte23() throws IOException {
        super();
    }

    @Override
    protected void writeString(String text, List<TextPosition> positions) throws IOException {

        // Só aplica a lógica até a linha 23
        if (lineCount <= 23 && !positions.isEmpty()) {

            float y = positions.get(0).getY();

            // Detecta GAP vertical maior que o normal → insere linha vazia
            if (lastY != -1 && (y - lastY) > 8) {
                super.writeString("\n");
                lineCount++; // linha vazia adicionada
            }

            lastY = y;
        }

        // Escreve a linha real
        super.writeString(text);
        super.writeString("\n");
        lineCount++;
    }
}
