package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Trees;
import org.antlr.v4.gui.TreeViewer;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;
import ca.ece.ubc.cpen221.parser.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.*;

public class CustomTests {

    // @Test
    public void test() throws IOException {
        YelpDB db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
        ToDoubleBiFunction<String, YelpDB> func = db.getPredictorFunction("QScfKdcxsa7t5qfE0Ev0Cw");

        /*
         * System.out.println(func.applyAsDouble("BJKIoQa5N2T_oDlLVf467Q", db)); //Price
         * is 2 System.out.println(func.applyAsDouble("h_we4E3zofRTf4G0JTEF0A", db));
         * //Price is 3 System.out.println(func.applyAsDouble("sxIPX4ZAipVl3ZCkkqXqZw",
         * db)); //Price is 4
         */

        System.out.println(db.kMeansClusters_json(8));
    }

    // @Test
    public void testJSON() throws IOException {
        System.out.println("\"name\": \"asdf\"");
        Gson gson = new Gson();
        YelpUser u = gson.fromJson("{\"name\": \"asdf\"}", YelpUser.class);
        System.out.println(u.getName());
    }

    // @Test
    public void testServerStuff() throws IOException {
        YelpDBServer serv = new YelpDBServer(7777);
        System.out.println(serv.requestParser("ADDUSER {\"name\": \"Jim\"}"));
    }

    @Test
    public void testANTLR() {

        CharStream stream = new ANTLRInputStream(
                "in(Telegraph Ave) && (category(Chinese) || category(Italian)) && (price <= 2 || price >=4)");

        QueryLexer lexer = new QueryLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);

        // For the real grammar, we should try creating the grammar first, and then add
        // handlers
        // whenever it leaves a certain context (refer to notes)

        // The listener would be its own class that extends "Name"BaseListener"
        // Create a parser that feeds of the token buffer

        QueryParser parser = new QueryParser(tokens);

        // Begin parsing at atom rule
        ParseTree tree = parser.andExpr();

        // show AST in GUI
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewr.setScale(1.5);// scale a little

        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        while (true)
            ;
    }

}
