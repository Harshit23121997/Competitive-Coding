#include<bits/stdc++.h>
using namespace std;
#define SZ(x) (int)(x).size()
#define dbg(x) cerr << #x << " is: " << x << endl
#define VI vector<int>
#define VII vector<vector<int> >
#define MP make_pair
#define PB push_back
#define PII pair<int,int>
// #define int long long
const int N = 1e6 + 10;
int n,r,m;
vector<int> g[N];
int mp[N];
int cd[N];
void bfs(int now, int md){
    vector<bool> vis(n,false);
    queue<int> Q;
    Q.push(now);
    vis[now] = true;
    mp[now]+=1;
    cd[now] = 0;
    while(!Q.empty() && cd[Q.front()] < md){
        int here = Q.front();
        Q.pop();
        for(auto to: g[here]){
            if(!vis[to]){
                vis[to] = true;
                mp[to] +=1;
                cd[to] = cd[here]+1;
                Q.push(to);
            }
        }
    }
}
void reset(){
    for(int i=0;i<N;i++){
        g[i].clear();
        mp[i]=0;
    }
}
int main(){
    #ifdef DEBUG
        freopen("input.txt","r",stdin);
    #endif
    int tt;
    cin >> tt;
    while(tt--){
        reset();
        cin >> n >> r >> m;
        for(int i=0;i<r;i++){
            int x,y;
            cin >> x >> y;
            x--;
            y--;
            g[x].PB(y);
            g[y].PB(x);
        }
        for(int i=0;i<m;i++){
            int now, md;
            cin >> now >> md;
            now--;
            bfs(now,md);
        }
        bool optimum = true;
        for(int i=0;i<n;i++){
            if(mp[i]>1 || mp[i] < 1){
                    optimum = false;
                    break;
            }
        }
        if(optimum)puts("Yes");
        else puts("No");
    }
    return 0;
}