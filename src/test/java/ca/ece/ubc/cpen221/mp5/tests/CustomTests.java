package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.gui.TreeViewer;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;
import ca.ece.ubc.cpen221.parser.*;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.*;

public class CustomTests {

    // @Test
    public void test() throws IOException {
        MP5Db<Restaurant> db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

        ToDoubleBiFunction<MP5Db<Restaurant>, String> func = db.getPredictorFunction("QScfKdcxsa7t5qfE0Ev0Cw");
        System.out.println(func.applyAsDouble(db, "BJKIoQa5N2T_oDlLVf467Q")); // Price is 2
        System.out.println(func.applyAsDouble(db, "h_we4E3zofRTf4G0JTEF0A")); // Price is 3
        System.out.println(func.applyAsDouble(db, "sxIPX4ZAipVl3ZCkkqXqZw")); // Price is 4

        // System.out.println(db.kMeansClusters_json(40xwxw));
    }

    // @Test
    public void testPredictorFunc() throws IOException {
        MP5Db<Restaurant> db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

        ToDoubleBiFunction<MP5Db<Restaurant>, String> func = db.getPredictorFunction("_NH7Cpq3qZkByP5xR4gXog");

        System.out.println(func.applyAsDouble(db, "BJKIoQa5N2T_oDlLVf467Q")); // Price is 2
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

    //@Test
    public void querySearch() throws IOException {
        YelpDBServer serv = new YelpDBServer(7777);
        System.out.println(serv.requestParser(
                "QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && (price <= 3 || price >=4) && (rating>2)"));
    }

    @Test
    public void testANTLR() {
        
        //https://stackoverflow.com/questions/30976962/nested-boolean-expression-parser-using-antlr

        @SuppressWarnings("deprecation")
        CharStream stream = new ANTLRInputStream(
                "in(Telegraph Ave) && (category(Chinese) || category(Italian)) && (price <= 2 || price >=4) && (rating>2)");

        QueryLexer lexer = new QueryLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);

        // For the real grammar, we should try creating the grammar first, and then add
        // handlers
        // whenever it leaves a certain context (refer to notes)

        // The listener would be its own class that extends "Name"BaseListener"
        // Create a parser that feeds of the token buffer

        QueryParser parser = new QueryParser(tokens);

        ParseTree tree = parser.root();

        // TEXTUAL VIEW//
        System.err.println(tree.toStringTree(parser));

        // LISTENER SETUP//
        ParseTreeWalker walker = new ParseTreeWalker();
        QueryListener listener = new QueryCreator();
        walker.walk(listener, tree);

        // VISUALIZATION//
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
