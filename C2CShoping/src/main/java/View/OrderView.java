package View;

import POJOs.ItemEntity;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class OrderView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map model, Document document,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        List<ItemEntity> cart=(List<ItemEntity>)model.get("cart");
        System.out.print(cart);
        double total=0;
        for(ItemEntity i:cart){
            document.add(new Paragraph("Item Name:" + i.getItemName()+"  Price:$"+i.getPrice()));
            total=total+i.getPrice();
       }
        document.add(new Paragraph("Total Price:" +total));
    }
}
