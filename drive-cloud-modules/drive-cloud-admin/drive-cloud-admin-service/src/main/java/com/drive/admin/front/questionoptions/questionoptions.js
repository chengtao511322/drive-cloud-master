import request from '@/utils/request'

                    
// 分页查询平台题目选项列表
export function listPageQuestionOptions(query) {
  return request({
    url: '/admin/questionOptions/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询平台题目选项列表
export function listQuestionOptions(query) {
  return request({
    url: '/admin/questionOptions/findList',
    method: 'post',
    data: query
  })
}

// 查询平台题目选项详细
export function getQuestionOptions(id) {
  return request({
    url: '/admin/questionOptions/' + id,
    method: 'get'
  })
}

// 新增平台题目选项
export function addQuestionOptions(data) {
  return request({
    url: '/admin/questionOptions',
    method: 'post',
    data: data
  })
}

// 修改平台题目选项
export function updateQuestionOptions(data) {
  return request({
    url: '/admin/questionOptions',
    method: 'put',
    data: data
  })
}

// 删除平台题目选项
export function delQuestionOptions(id) {
  return request({
    url: '/admin/questionOptions/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台题目选项
export function changeStatus(data) {
  return request({
    url: '/admin/questionOptions/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台题目选项
export function exportQuestionOptions(query) {
  return request({
    url: '/admin/questionOptions/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}