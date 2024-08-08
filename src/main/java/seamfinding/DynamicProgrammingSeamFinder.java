package seamfinding;

import seamfinding.energy.EnergyFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {

    @Override
    public List<Integer> findHorizontal(Picture picture, EnergyFunction f) {
        // TODO: Replace with your code 
        int width = picture.width();
        int height = picture.height();
        double[][] energy = new double[width][height];
        int[][] premin = new int[width][height];

        for (int y = 0; y < height; y++) {
            energy[0][y] = f.apply(picture, 0, y);
            premin[0][y] = 0;
        }

        // Fill in the rest of the table
        for (int x = 1; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double minEnergy = energy[x - 1][y];
                premin[x][y] = y;

                // adjusting for the upper edge
                if (y > 0 && energy[x - 1][y - 1] < minEnergy) {
                    minEnergy = energy[x - 1][y - 1];
                    premin[x][y] = y - 1;
                }
                // adjusting for the bottom edge

                if (y + 1< height && energy[x - 1][y + 1] < minEnergy) {
                    minEnergy = energy[x - 1][y + 1];
                    premin[x][y] = y + 1;
                }
                energy[x][y] = f.apply(picture, x, y) + minEnergy;

/*
                if (energy[x-1][y-1]<minEnergy){
                    minEnergy = energy[x-1][y-1];
                    premin[x][y] = y-1;
                }
                if (energy[x-1][y+1]<minEnergy){
                    minEnergy = energy[x-1][y+1];
                    premin[x][y]= y +1;
                }

 */
            }
        }

        // Find the end of the minimum energy seam
        double min = Double.POSITIVE_INFINITY;
        int minIndex = -1;
        List<Integer> seam = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            if (energy[width - 1][y] < min) {
                min = energy[width - 1][y];
                minIndex = y;
            }
        }
        seam.add(minIndex);

        // Backtrack to find the seam path
        int minEnergyY = premin[width-1][minIndex];
        for (int x = width - 2; x >= 0; x--) {
            seam.add(minEnergyY);
            minEnergyY = premin[x][minEnergyY];
        }

        Collections.reverse(seam);
        return seam;
    }
}