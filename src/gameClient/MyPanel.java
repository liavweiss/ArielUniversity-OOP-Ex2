package gameClient;

import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * This class represent a GUI of the Pokemon game.
 */
public class MyPanel extends JPanel {

    private Arena _ar;
    private gameClient.util.Range2Range _w2f;

    /**
     * Default constructor.
     * @param ar
     */
    public MyPanel(Arena ar) {
        super();
        this._ar = ar;
        this.setBackground(Color.gray);
        updateFrame();
    }

    /**
     * This method is responsible for refreshing the frame according to the current state of the game.
     */
    private void updateFrame() {
        Range rx = new Range(20, this.getWidth() - 20);
        Range ry = new Range(this.getHeight() - 10, 150);
        Range2D frame = new Range2D(rx, ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g, frame);
    }

    /**
     * This method is responsible for creating the graphical window that represents the game.
     * @param g - Graphics
     */
    protected void paintComponent(Graphics g) {
        int w = this.getWidth();
        int h = this.getHeight();
        g.clearRect(0, 0, w, h);
        updateFrame();
        super.paintComponent(g);
        updateFrame();
        drawGraph(g);
        drawPokemons(g);
        drawAgents(g);
        drawValue(g);
    }


    /**
     * This method draw graph on a frame.
     * To do so it used "drawNode" and "drawEdge".
     * @param g - Graphics
     */
    private void drawGraph(Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        Iterator<node_data> iter = gg.getV().iterator();
        while (iter.hasNext()) {
            node_data n = iter.next();
            g.setColor(Color.BLACK);
            drawNode(n, 5, g);
            Iterator<edge_data> itr = gg.getE(n.getKey()).iterator();
            while (itr.hasNext()) {
                edge_data e = itr.next();
                g.setColor(Color.WHITE);
                drawEdge(e, g);
            }
        }
    }


    /**
     * This method draw an oval on the frame.
     * The oval represent a Pokemon on a graph.
     * Green oval means this pokemon is on ascending edge (aka 2-->7).
     * Orange oval means this pokemon is on descending edge (aka 8-->4).
     * @param g - Graphics
     */
    private void drawPokemons(Graphics g) {
        List<CL_Pokemon> poks = _ar.getPokemons();
        if (poks != null) {
            Iterator<CL_Pokemon> itr = poks.iterator();
            while (itr.hasNext()) {
                CL_Pokemon pok = itr.next();
                Point3D c = pok.getLocation();
                int r = 10;
                g.setColor(Color.green);
                if (pok.getType() < 0) {
                    g.setColor(Color.orange);
                }
                if (c != null) {
                    geo_location fp = this._w2f.world2frame(c);
                    g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
                    g.setColor(Color.black);
                    g.drawString(""+(int)pok.getValue(),(int) fp.x()-(r/2),(int) fp.y()-(r/2));
                }
            }
        }
    }

    /**
     * This method draw an oval on the frame.
     * The oval represent an agent on a graph.
     * @param g - Graphics
     */
    private void drawAgents(Graphics g) {
        List<CL_Agent> rs = _ar.getAgents();
        g.setColor(Color.red);
        int i = 0;
        while (rs != null && i < rs.size()) {
            geo_location c = rs.get(i).getLocation();
            int r = 8;
            i++;
            if (c != null) {
                geo_location fp = this._w2f.world2frame(c);
                g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
            }
        }
    }

    /**
     * This method draw an oval on the frame.
     * The oval represent a node of the graph.
     * @param g - Graphics
     */
    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
        g.drawString("" + n.getKey(), (int) fp.x(), (int) fp.y() - 4 * r);
    }

    /**
     * This method draw a line on the frame.
     * The line represent a directed weighted edge of the graph.
     * @param g - Graphics
     */
    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        g.drawLine((int) s0.x(), (int) s0.y(), (int) d0.x(), (int) d0.y());
    }

    /**
     * This method draw a String on the frame.
     * The String represent the current score in the game.
     * @param g - Graphics
     */
    private void drawValue (Graphics g){
        List<CL_Agent> agents = _ar.getAgents();
        g.setColor(Color.BLACK);
        g.setFont(new Font("ARIEL", Font.BOLD, 20));
        int score = 0;
        if(agents != null) {
            int i = 0;
            for (CL_Agent agent : agents) {
                score += agent.getValue();
                g.drawString("Agent " + agent.getID() +" : " + agent.getValue() + " ( speed -> " + agent.getSpeed() +" ) ",15 , 80+i*30);
                i++;
            }
        }
        g.drawString("Score: " + score,15 , 50);
    }
}





