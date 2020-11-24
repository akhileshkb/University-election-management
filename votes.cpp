#include<iostream>
#include<vector>
#include<cstring>
#include<fstream>
#include<cstdlib>
#include<ctime>
#include<set>
using namespace std;

int main()
{
    ifstream is("C:/Users/satis/Desktop/DBMS-Lab/University-election-management/studentdata.sql");
    ofstream os("C:/Users/satis/Desktop/DBMS-Lab/University-election-management/votesdata.sql");
    vector<string> club {"club1", "club2","club3","club4","club5","club6","club7","club8","club9"};
    string str; getline(is, str);
    srand(time(0));
    os<<"delete from voter;\n";
    set<string> votesset;
    for(str; getline(is, str);){
        int n1 = str.find_first_of("(");
        int n2 = str.find_first_of(",");
        string s="insert into voter values ";
        for(int i=n1;i<=n2;i++) s+=str[i];
        n1 = 1 + rand()%7;
        for(int i=0;i<n1;i++){
            n2 = rand()%9;
            string s1 = s+ " \'" + club[n2] + "\');";
            votesset.insert(s1);
        }
    }
    for(string s:votesset) os<<s<<"\n";
    cout<<votesset.size();
    return 0;
}
