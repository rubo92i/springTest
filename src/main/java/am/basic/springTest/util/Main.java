package am.basic.springTest.util;

import am.basic.springTest.model.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Comment comment = new Comment();
        comment.setId(15);
        comment.setName("Namfjdfkgnfsjkfm");
        comment.setDescription("sfdgfsdfgsd");
        comment.setUserId(14);

        ObjectMapper objectMapper = new ObjectMapper();


        String jsonResult = objectMapper.writeValueAsString(comment);


        XmlMapper mapper = new XmlMapper();

        String xmlResult = mapper.writeValueAsString(comment);

        System.out.println(jsonResult);

        System.out.println(xmlResult);

    }
}
