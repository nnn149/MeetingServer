/**
 * FileName: DownloadExcelController
 * Author:   10418
 * Date:     2019-12-10 16:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈下载excel控制器〉
 *
 * @author 10418
 * @create 2019-12-10
 * @since 1.0.0
 */
@RequestMapping("/download")
@Controller
public class DownloadExcelController {

    @GetMapping(value = "/customerExcelTemplate")
    public void customerExcelTemplate(HttpServletResponse response) {
        try {
            String fileName = "customerTemplate.xlsx";
            InputStream is = this.getClass().getResourceAsStream("/excel/customerTemplate.xlsx");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attchement;filename=" + fileName);
            OutputStream os = response.getOutputStream();
            os.write(is.readAllBytes());
            os.flush();
            os.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
