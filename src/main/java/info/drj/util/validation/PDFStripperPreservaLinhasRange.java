package info.drj.util.validation;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PDFStripperPreservaLinhasRange extends PDFTextStripper {

    private float lastY = -1f;
    private int lineCount = 0;

    private final int startIndex; // primeira linha onde aplicamos a lógica (ex: 7)
    private final int endIndex;   // última linha onde aplicamos a lógica (ex: 23)
    private final float gapThreshold; // threshold em unidades do PDF (ex: 8.0f)

    public PDFStripperPreservaLinhasRange(int startIndex, int endIndex, float gapThreshold) throws IOException {
        super();
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.gapThreshold = gapThreshold;
    }

    @Override
    protected void writeString(String text, List<TextPosition> positions) throws IOException {

        // Se não houver posições de texto (caso raro), apenas escrevemos
        if (positions == null || positions.isEmpty()) {
            super.writeString(text);
            return;
        }

        float y = positions.get(0).getY();

        // Aplicar a lógica de "inserir linha vazia" somente se estivermos
        // dentro do intervalo de linhas que queremos tratar.
        // Usamos lineCount como contador de quantas linhas já foram escritas.
        if (lineCount >= startIndex && lineCount <= endIndex) {

            if (lastY != -1f) {
                float delta = y - lastY;

                // Se o salto vertical for maior que threshold -> insere linha vazia
                if (delta > gapThreshold) {
                    super.writeString("\n"); // insere linha vazia
                    lineCount++; // contamos a linha vazia inserida
                }
            }
        }

        // Depois escrevemos a linha atual (texto)
        super.writeString(text);
        super.writeString("\n");
        lineCount++;

        // Atualiza lastY para a próxima comparação
        lastY = y;
    }

    // Reinicia contadores caso o stripper seja reutilizado em outro doc/página
//    @Override
//    protected void startDocument(Writer output) throws IOException {
//        super.startDocument(output);
//        lastY = -1f;
//        lineCount = 0;
//    }
}
