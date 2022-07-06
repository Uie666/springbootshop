/*用export把方法暴露出来*/

/*设置cookie*/

export function setCookie(c_name,value,expire) {

    var date=new Date()

    date.setSeconds(date.getSeconds()+expire)

    document.cookie=c_name+ "="+encodeURI(value)+"expires="+date.toUTCString()



}

/*获取cookie*/

export function getCookie(c_name){

    if (document.cookie.length>0){

        let c_start=document.cookie.indexOf(c_name + "=")

        if (c_start!=-1){

            c_start=c_start + c_name.length+1

            let c_end=document.cookie.indexOf(";",c_start)

            if (c_end==-1) c_end=document.cookie.length

            return decodeURI(document.cookie.substring(c_start,c_end))

        }

    }

    return ""

}

/*删除cookie*/

export function delCookie(c_name){

    setCookie(c_name, "", -1)

}
