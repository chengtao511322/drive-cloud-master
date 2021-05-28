import request from '@/utils/request'

                                  
// 分页查询平台题库表列表
export function listPageQuestionBank(query) {
  return request({
    url: '/admin/questionBank/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询平台题库表列表
export function listQuestionBank(query) {
  return request({
    url: '/admin/questionBank/findList',
    method: 'post',
    data: query
  })
}

// 查询平台题库表详细
export function getQuestionBank(id) {
  return request({
    url: '/admin/questionBank/' + id,
    method: 'get'
  })
}

// 新增平台题库表
export function addQuestionBank(data) {
  return request({
    url: '/admin/questionBank',
    method: 'post',
    data: data
  })
}

// 修改平台题库表
export function updateQuestionBank(data) {
  return request({
    url: '/admin/questionBank',
    method: 'put',
    data: data
  })
}

// 删除平台题库表
export function delQuestionBank(id) {
  return request({
    url: '/admin/questionBank/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台题库表
export function changeStatus(data) {
  return request({
    url: '/admin/questionBank/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台题库表
export function exportQuestionBank(query) {
  return request({
    url: '/admin/questionBank/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}