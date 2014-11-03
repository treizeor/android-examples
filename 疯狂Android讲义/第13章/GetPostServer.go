package main
import (
	"fmt"
	"net/http"
	"strings"
	"log"
)

func sayHelloName(w http.ResponseWriter, r *http.Request){
	r.ParseForm() //解析参数，默认是不会解析的
	fmt.Println(r.Form) //这些信息是输出到服务端的打印信息
	fmt.Println("path", r.URL.Path)
	fmt.Println("scheme", r.URL.Scheme)
	fmt.Println(r.Form["url_log"])
	for k, v := range r.Form {
		fmt.Println("key:", k)
		fmt.Println("val:", strings.Join(v, ""))
	}
	fmt.Fprintf(w, "Hello treize!")
}

func main(){
	http.HandleFunc("/", sayHelloName) //设置访问的路由
	err := http.ListenAndServe(":9999", nil) //设置监听端口
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}
