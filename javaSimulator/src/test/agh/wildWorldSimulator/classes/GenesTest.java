package agh.wildWorldSimulator.classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenesTest {

    int length = 32;
    int range = 8;

    @Test
    public void randomGenes() {
        Genes randomGenes = new Genes(length, range);
        int[] genes = randomGenes.getGenesArray();
        boolean[] values = new boolean[randomGenes.getGenesRange()];
        for (int value : genes) {
            values[value] = true;
        }
        for (boolean flag : values) {
            assertTrue(flag);
        }
    }

    @Test
    public void mutatedGenes() {
        Genes firstGenes = new Genes(length, range);
        Genes secondGenes = new Genes(length, range);
        Genes mutatedGenes = new Genes(firstGenes, secondGenes);
        int[] genes = mutatedGenes.getGenesArray();
        boolean[] values = new boolean[mutatedGenes.getGenesRange()];
        for (int value : genes) {
            values[value] = true;
        }
        for (boolean flag : values) {
            assertTrue(flag);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void differentLengthGenes() {
        Genes firstGenes = new Genes(length-1, range);
        Genes secondGenes = new Genes(length, range);
        Genes mutatedGenes = new Genes(firstGenes, secondGenes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void differentRangeGenes() {
        Genes firstGenes = new Genes(length, range);
        Genes secondGenes = new Genes(length, range+1);
        Genes mutatedGenes = new Genes(firstGenes, secondGenes);
    }

    @Test
    public void fixGenes() {
        int length = 10;
        int range = 4;
        Genes genesToFix = new Genes(length, range);
        genesToFix.setGenesArray(new int[]{0, 0, 1, 1, 3, 3, 0, 1, 3, 1});
        // check if gene 2 is missing
        int[] genes = genesToFix.getGenesArray();
        boolean[] values = new boolean[genesToFix.getGenesRange()];
        for (int value : genes) {
            values[value] = true;
        }
        assertFalse(values[2]);

        // fix genes
        genesToFix.fixGenes();

        // check if now all genes occur
        genes = genesToFix.getGenesArray();
        values = new boolean[genesToFix.getGenesRange()];
        for (int value : genes) {
            values[value] = true;
        }
        for (boolean flag : values) {
            assertTrue(flag);
        }
    }
}