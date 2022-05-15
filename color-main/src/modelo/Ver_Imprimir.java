package modelo;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * @author Luis Fernando Paxel
 */
public class Ver_Imprimir implements Printable {

    private int id;
    private String datos;
    private String Datos2;

    public Ver_Imprimir() {
    }

    public Ver_Imprimir(String datos) {
        this.datos = datos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getDatos2() {
        return Datos2;
    }

    public void setDatos2(String Datos2) {
        this.Datos2 = Datos2;
    }

    public void imprimir() {
        //  PrinterJob job = PrinterJob.getPrinterJob();
        PrinterJob job = PrinterJob.getPrinterJob();

        job.setPrintable(new Ver_Imprimir(datos));
        if (job.printDialog()) {
            try {
                job.print();
                JOptionPane.showMessageDialog(null, "Imprimiendo... ", "Impresora", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Imagenes/Impresoras.png"));

            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Error en la impresión  " + ex, "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Imagenes/Cancelado.png"));

            }
        } else {
            JOptionPane.showMessageDialog(null, "Se cancelo la impresión ", "Cancelar", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Imagenes/Cancelado.png"));

        }
    }

    public String ObtenerCadena() {
        String Cadena = this.getDatos();
        return Cadena;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        int x = (int) pageFormat.getImageableX();
        int y = (int) pageFormat.getImageableY();
        g2d.translate(x, y);
        Font font = new Font("Serif", Font.PLAIN, 10);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int lineHeight = metrics.getHeight();

        BufferedReader br = new BufferedReader(new StringReader(datos));
        try {
            String cadena;
            x += 15;
            y += 30;
            while ((cadena = br.readLine()) != null) {
                y += lineHeight;
                g2d.drawString(cadena, x, y);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al imprimir el texto " + ex, "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Imagenes/Cancelado.png"));

        }

        return PAGE_EXISTS;
    }
}
