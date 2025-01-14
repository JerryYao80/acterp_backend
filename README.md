辅助生殖ERP系统设计需求文档
系统概述
设计并开发一套完整的辅助生殖ERP系统，实现客户管理、业务管理、资源管理和财务管理的全流程数字化。系统应具备高度的安全性、可扩展性和用户友好性。

核心价值
提供完整的客户生命周期管理
实现业务流程的标准化和自动化
优化资源配置和使用效率


技术架构
    后端：
        编程语言：Java
        框架：Spring Boot
        数据库：PostgreSQL
        消息队列：RabbitMQ
    前端：
        框架/库：React
        状态管理：Redux
        UI组件库：Material-UI

功能模块详细说明
1. 客户管理模块
1.1 客户总览
构建所有客户可视化界面，展示：
{
  "显示指标": [
    "活跃客户总数",
    "各地区客户分布热力图",
    "客户类型占比饼图",
    "新增客户趋势曲线"
  ],
  "操作功能": [
    "客户信息快速检索",
    "批量导入导出",
    "客户标签管理",
    "客户分类筛选"
  ]
}
1.2 客户详情
实现多维度客户信息管理：
{
  "基础信息": {
    "社会信息": ["个人基本信息", "职业收入状况", "家庭背景调查"],
    "需求信息": ["服务需求详情", "预算范围", "时间要求"],
    "生理信息": ["健康检查记录", "生育能力评估", "遗传病史"]
  },
  "评估体系": {
    "质量评分": ["信用评级", "支付能力", "配合度"],
    "风险等级": ["法律风险", "医疗风险", "信用风险"],
    "处理建议": ["推荐方案", "注意事项", "跟进策略"]
  }
}
1.3 风险预警
建立多层次客户风险监控体系：
{
  "预警类型": {
    "信用风险": ["违约倾向", "付款延迟", "不良记录"],
    "服务风险": ["投诉倾向", "满意度下降", "服务中断"],
    "法律风险": ["合同争议", "监管合规", "政策变动"]
  },
  "预警等级": ["红色预警", "橙色预警", "黄色预警"],
  "响应机制": ["自动通知", "人工介入", "应急处置"]
}

2. 业务管理模块
2.1 业务总览
构建所有业务可视化界面，展示：
{
  "业务总览": ["业务数量统计", “每个阶段数量统计”，"业务时空分布", "代孕类型数量统计"],
}
2.2 业务流程管理
实现业务全流程数字化跟踪：
{
  "获客阶段": {
    "营销管理": ["广告投放", "渠道分析", "转化跟踪"],
    "方案制定": ["需求评估", "套餐定制", "价格谈判"],
    "合同管理": ["模板生成", "电子签约", "存档管理"]
  },
  "服务阶段": {
    "医疗服务": {
      "体外受精": ["OCR读取检查报告", "异常处理", "方案调整"],
      "胚胎移植": ["OCR读取检查报告", "医疗资源调度", "异常处理"],
      "孕期护理": ["OCR读取检查报告", "异常处理", "营养方案"],
      "新儿分娩"：["OCR读取检查报告", "医疗资源调度", "亲子鉴定"]
    },
    "辅助服务": {
      "入境服务": ["签证办理", "通关协助", "住宿安排"],
      "落户服务": ["材料准备", "程序办理", "进度跟踪"]
    }
  }
}
2.3 服务跟踪
跟踪整个服务阶段所有检查结果、状态以及重要里程碑节点变化
{
    “过程服务”：{
    “检查单录入”：["信息读取", "结果分析"]，
    “用户告知”：["内容编辑", "短信发送"]，
    “自媒体发布”：["内容编辑", "小红书发布"，“抖音发布”，“推特发布”，“微信朋友圈发布”]
    }
}

3. 资源管理模块
3.1 资源盘点
构建所有资源的可视化界面，展示：
{
    “供体资源”: ["时空分布", "使用状态", "资源结余"],
    “母体资源”: ["时空分布", "使用状态", "资源结余"],
    “医疗资源”: ["时空分布", "使用状态", "资源结余"],
    “月子资源”: ["时空分布", "使用状态", "资源结余"],
    “人力资源”: ["时空分布", "使用状态", "资源结余"],
    “线下联络资源”: ["时空分布", "使用状态", "资源结余"],
    “线上媒体资源”: ["平台分布", ],
}
3.2 资源详情
展示每类每个资源的详细情况，包括：
{
    “供体资源”: ["属性", "质量", "位置"，"历史", "费用"],
    “母体资源”: ["属性", "质量", "位置"，"历史", "费用"],
    “医疗资源”: ["属性", "质量", "位置"，"历史", "费用"],
    “月子资源”: ["属性", "质量", "位置"，"历史", "费用"],
    “人力资源”: ["属性", "质量", "位置"，"历史", "费用"],
    “线下联络资源”: ["属性", "质量", "位置"，"历史", "费用"],
    “线上媒体资源”: ["属性", "质量", "历史", "费用"]
}
3.3 资源配置
优化资源调度和分配：
{
  "资源调度": {
    "计划调度": ["资源预约", "档期安排", "使用追踪"],
    "应急调度": ["紧急调配", "备选方案", "临时替换"]
  }
}

4. 财务管理模块
4.1 财务总览
实现公司财务总体状况：
{
    "收入管理": ["服务收费", "分期收款", "其他收入"],
    "支出管理": ["资源成本", "运营开支", "异常支出"],
    "利润分析": ["毛利统计", "净利分析", "趋势预测"]
}
4.2 财务详情
{
  "资金管理": {
    "现金流": ["日常收支", "资金周转", "应急储备"],
    "信贷管理": ["借贷记录", "还款计划", "额度控制"]
  }
  "开支明细": ["OCR提取供体转账记录", "OCR提取母体转账记录", "OCR提取获客平台转账记录", "OCR提取获客联络人转账记录", "OCR提取医疗中心转账记录", "OCR提取月子中心转账记录", "OCR提取人工转账记录", "OCR提取办公转账记录", "OCR提取出差转账记录","违约赔付", "重做消耗", "商业借贷信息","发送用户催款短信"]
}

4.3 风险预警
因为现金流少、借贷偿还期满、违约赔付、用户缴费延期等原因导致的财务风险。


5 整体态势
首页是态势大屏，用于展示辅助生殖业务的时空分布和各个业务实施过程中的风险预警

6 用户注册登录管理
