package info.drj.controller;

import java.util.regex.*;

public class ExtratorFaturaBean {

    private static String extrairPorRegex(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find() && matcher.groupCount() >= 1) {
            return matcher.group(1).trim();
        }
        return null; // retorna null se não achou nada
    }

    public static String getNotaFiscal(String texto) {
        return extrairPorRegex(texto, "(?:Nota Fiscal.*?Nº|NOTA FISCAL Nº)\\s*(\\d+)");
    }

    public static String getDataEmissao(String texto) {
        return extrairPorRegex(texto, "(?:Data de Emissão|EMISSÃO:)\\s*(\\d{2}/\\d{2}/\\d{4})");
    }

    public static String getNomeCooperado(String texto) {
        return extrairPorRegex(texto, "(?m)^(?!CERPAL)[A-Z][A-Z ]{5,}$");
    }

    public static String getCpf(String texto) {
        return extrairPorRegex(texto, "CPF[:\\s]*(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})");
    }

    public static String getCnpj(String texto) {
        return extrairPorRegex(texto, "CNPJ[:\\s]*(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})");
    }

    public static String getInscricaoEstadual(String texto) {
        return extrairPorRegex(texto, "Inscriç(?:ão|ÃO) Estadual[:\\s]*(IE-[\\d\\.]+)");
    }

    public static String getCodigoInstalacao(String texto) {
        return extrairPorRegex(texto, "DA INSTALAÇÃO\\s*(\\d+)");
    }

    public static String getMatricula(String texto) {
        return extrairPorRegex(texto, "(?:Matric\\.?|CODIGO DO CLIENTE)\\s*(\\d+)");
    }

    public static String getReferencia(String texto) {
        return extrairPorRegex(texto, "(?:CONTA MES|REF: MÊS / ANO)\\s*(\\d{2}/\\d{4})");
    }

    public static String getVencimento(String texto) {
        return extrairPorRegex(texto, "VENCIMENTO\\s*(\\d{2}/\\d{2}/\\d{4})");
    }

    public static String getValorTotal(String texto) {
        // Primeiro tenta pelo label "TOTAL A PAGAR"
        String valor = extrairPorRegex(texto, "TOTAL A PAGAR\\s*([\\d,.]+)");
        if (valor == null) {
            // Se não achar, tenta pelo "Valor do Documento"
            valor = extrairPorRegex(texto, "Valor do Documento\\s*([\\d,.]+)");
        }
        return valor;
    }

    public static String getConsumo(String texto) {
        return extrairPorRegex(texto, "(?:Venda de Energia \\(KWh\\)|Consumo\\s*kWh)\\s*(\\d+)");
    }

    public static String getLeituraAnterior(String texto) {
        return extrairPorRegex(texto, "Leitura Anterior\\s*(\\d+)");
    }

    public static String getLeituraAtual(String texto) {
        return extrairPorRegex(texto, "Leitura Atual\\s*(\\d+)");
    }

    public static String getMedidor(String texto) {
        return extrairPorRegex(texto, "Medidor\\s*(\\d+)");
    }

    public static String getEndereco(String texto) {
        // Captura bloco de endereço (Chacara, F-PARADA, PALMITAL)
        return extrairPorRegex(texto, "(?m)(Chacara.*PALMITAL)");
    }

}
