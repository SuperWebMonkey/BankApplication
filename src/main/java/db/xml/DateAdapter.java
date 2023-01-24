package db.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date>{
    @Override
    public Date unmarshal(String s) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(s);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
