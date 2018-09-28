package com.czxx.campusmanagement.controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.account.GetLineInput;

@Controller
@RequestMapping("pipaxing")
public class PiPaXingController {
	
	@RequestMapping(value = "getline", method = RequestMethod.POST)
	@ResponseBody
	public Result getLine(@RequestBody GetLineInput input,HttpServletResponse response) throws InterruptedException
	{
		ArrayList<String> strings = new ArrayList();
		strings.add("浔阳江头夜送客，");
		strings.add("枫叶荻花秋瑟瑟。");
		strings.add("主人下马客在船，");
		strings.add("举酒欲饮无管弦。");
		strings.add("醉不成欢惨将别，");
		strings.add("别时茫茫江浸月。");
		strings.add("忽闻水上琵琶声，");
		strings.add("主人忘归客不发。");
		strings.add("寻声暗问弹者谁，");
		strings.add("琵琶声停欲语迟。");
		strings.add("移船相近邀相见，");
		strings.add("添酒回灯重开宴。");
		strings.add("千呼万唤始出来，");
		strings.add("犹抱琵琶半遮面。");
		strings.add("转轴拨弦三两声，");
		strings.add("未成曲调先有情。");
		strings.add("弦弦掩抑声声思，");
		strings.add("似诉平生不得志。");
		strings.add("低眉信手续续弹，");
		strings.add("说尽心中无限事。");
		strings.add("轻拢慢捻抹复挑，");
		strings.add("初为《霓裳》后《六幺》。");
		strings.add("大弦嘈嘈如急雨，");
		strings.add("小弦切切如私语。");
		strings.add("嘈嘈切切错杂弹，");
		strings.add("大珠小珠落玉盘。");
		strings.add("间关莺语花底滑，");
		strings.add("幽咽泉流冰下难。");
		strings.add("冰泉冷涩弦凝绝，");
		strings.add("凝绝不通声暂歇。");
		strings.add("别有幽愁暗恨生，");
		strings.add("此时无声胜有声。");
		strings.add("银瓶乍破水浆迸，");
		strings.add("铁骑突出刀枪鸣。");
		strings.add("曲终收拨当心画，");
		strings.add("四弦一声如裂帛。");
		strings.add("东船西舫悄无言，");
		strings.add("唯见江心秋月白。");
		strings.add("沉吟放拨插弦中，");
		strings.add("整顿衣裳起敛容。");
		strings.add("自言本是京城女，");
		strings.add("家在虾蟆陵下住。");
		strings.add("十三学得琵琶成，");
		strings.add("名属教坊第一部。");
		strings.add("曲罢曾教善才服，");
		strings.add("妆成每被秋娘妒。");
		strings.add("五陵年少争缠头，");
		strings.add("一曲红绡不知数。");
		strings.add("钿头银篦击节碎，");
		strings.add("血色罗裙翻酒污。");
		strings.add("今年欢笑复明年，");
		strings.add("秋月春风等闲度。");
		strings.add("弟走从军阿姨死，");
		strings.add("暮去朝来颜色故。");
		strings.add("门前冷落鞍马稀，");
		strings.add("老大嫁作商人妇。");
		strings.add("商人重利轻别离，");
		strings.add("前月浮梁买茶去。");
		strings.add("去来江口守空船，");
		strings.add("绕船月明江水寒。");
		strings.add("夜深忽梦少年事，");
		strings.add("梦啼妆泪红阑干。");
		strings.add("我闻琵琶已叹息，");
		strings.add("又闻此语重唧唧。");
		strings.add("同是天涯沦落人，");
		strings.add("相逢何必曾相识！");
		strings.add("我从去年辞帝京，");
		strings.add("谪居卧病浔阳城。");
		strings.add("浔阳地僻无音乐，");
		strings.add("终岁不闻丝竹声。");
		strings.add("住近湓江地低湿，");
		strings.add("黄芦苦竹绕宅生。");
		strings.add("其间旦暮闻何物？");
		strings.add("杜鹃啼血猿哀鸣。");
		strings.add("春江花朝秋月夜，");
		strings.add("往往取酒还独倾。");
		strings.add("岂无山歌与村笛？");
		strings.add("呕哑嘲哳难为听。");
		strings.add("今夜闻君琵琶语，");
		strings.add("如听仙乐耳暂明。");
		strings.add("莫辞更坐弹一曲，");
		strings.add("为君翻作《琵琶行》。");
		strings.add("感我此言良久立，");
		strings.add("却坐促弦弦转急。");
		strings.add("凄凄不似向前声，");
		strings.add("满座重闻皆掩泣。");
		strings.add("座中泣下谁最多？");
		strings.add("江州司马青衫湿。");
		Result result = new Result();
		if(input.getLineNumber()>87)
		{
			result.setCode(0);
			result.setMessage("获取失败，不得大于87");
			result.setResult(null);
		}
		String line = strings.get(input.getLineNumber());
		Random rand = new Random();
		Thread.sleep(6000);
		
		result.setCode(1);
		result.setMessage("获取成功");
		result.setResult(line);
		
		return result;
	}
}
