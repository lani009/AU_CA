#define TRUE 1
#define FALSE 0

int main() {
    int flag[30];
    do {
        flag[i] = TRUE;

        while (flag[j]) {
            if (turn == j) {
                flag[i] = FALSE;
                while (turn == j)
                    ; // do nothing
                flag[i] = TRUE;
            }

            // CRITAL SECTION

            turn = j;
            flag[i] = FALSE;

            // REMAINDER SECTION
        }
    } while (TRUE);
}