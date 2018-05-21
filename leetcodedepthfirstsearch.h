#ifndef LEETCODEDEPTHFIRSTSEARCH
#define LEETCODEDEPTHFIRSTSEARCH

#include <vector>

using namespace std;

//733. Flood Fill
void floodFill(vector<vector<int> > &image, int sr, int sc, int oldColor, int newColor)
{
    if (sr >= 0 && sr < image.size() && sc >= 0 && sc < image[0].size()) {
        if (image[sr][sc] == oldColor && image[sr][sc] != newColor) {
            image[sr][sc] = newColor;
            floodFill(image, sr - 1, sc, oldColor, newColor);
            floodFill(image, sr + 1, sc, oldColor, newColor);
            floodFill(image, sr, sc - 1, oldColor, newColor);
            floodFill(image, sr, sc + 1, oldColor, newColor);
        }
    }
}

vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor)
{
    int color = image[sr][sc];

    floodFill(image, sr, sc, color, newColor);

    return image;
}

#endif // LEETCODEDEPTHFIRSTSEARCH

