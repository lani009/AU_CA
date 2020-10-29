#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
#include <cstdlib>
#include <ctime>

#define NUM_PROCESS 10

using namespace std;

typedef struct Process{
	int brst;
	int arrv;
}Process;

int chk_arrival(int);
int arr[NUM_PROCESS+1]={0};

bool cmp(Process &a, Process &b){
	if(a.arrv < b.arrv){
		return true;
	}
	return false;
}

int main(){
	vector<Process> v;
	srand((unsigned int) time(NULL));
	
	for(int i = 0; i< NUM_PROCESS; i++){

		Process tmp;
		tmp.brst= rand()%10 + 1;
		while(1){
			tmp.arrv =rand()%20 + 1;
			if(chk_arrival(tmp.arrv)){
				break;
			}
		}

		v.push_back(tmp);
	}	
	sort(v.begin(), v.end(), cmp);

	ofstream ofp("input.txt");
	if(ofp.is_open()){
		for(auto i : v){
			ofp << i.brst <<" "<< i.arrv << endl;
		}	
	}
	ofp.close();
}

int chk_arrival(int time){
	if(!arr[time]){
		arr[time] = 1;
		return 1;
	}else{
		return 0;
	}
}
