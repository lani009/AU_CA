#include<stdio.h>
#include<string.h>

int main()
{
    char pn[50][50],t[50];
    int arr[50],bur[50],star[50],finish[50],tat[50],wt[50],i,j,n,temp;
    int totwt=0,tottat=0;
    int tmp_result;
    i=0;
    FILE *fp = fopen("input.txt", "r");
    while ((tmp_result = fscanf(fp, "%d %d",&bur[i],&arr[i])) != EOF) {
        pn[i][0] = 'p';
        pn[i][1] = '1'+i;
        pn[i][2] = 0x00;
        i++;
    }
    n=i;
    fclose(fp);
    

    for(i=0; i<n; i++)
    {
        for(j=0; j<n; j++)
        {
            if(arr[i]<arr[j])
            {
                temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                temp=bur[i];
                bur[i]=bur[j];
                bur[j]=temp;
                strcpy(t,pn[i]);
                strcpy(pn[i],pn[j]);
                strcpy(pn[j],t);
            }
 
        }
    }
    for(i=0; i<n; i++)
    {
        if(i==0)
            star[i]=arr[i];
        else
            star[i]=finish[i-1];
        wt[i]=star[i]-arr[i];
        finish[i]=star[i]+bur[i];
        tat[i]=finish[i]-arr[i];
    }
    printf("\nPName Arrtime Burtime WaitTime Start TAT Finish");
    for(i=0; i<n; i++)
    {
        printf("\n%s\t%3d\t%3d\t%3d\t%3d\t%6d\t%6d",pn[i],arr[i],bur[i],wt[i],star[i],tat[i],finish[i]);
        totwt+=wt[i];
        tottat+=tat[i];
    }
    printf("\nAverage Waiting time:%f",(float)totwt/n);
    printf("\nAverage Turn Around Time:%f",(float)tottat/n);
    return 0;
}