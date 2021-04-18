import request from '@/utils/request'


// 分页查询支付交易流水信息表列表
export function listPageMentTransaction(query) {
  return request({
    url: '/pay/mentTransaction/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询支付交易流水信息表列表
export function listMentTransaction(query) {
  return request({
    url: '/pay/mentTransaction/findList',
    method: 'post',
    data: query
  })
}

// 查询支付交易流水信息表详细
export function getMentTransaction(id) {
  return request({
    url: '/pay/mentTransaction/' + id,
    method: 'get'
  })
}

// 新增支付交易流水信息表
export function addMentTransaction(data) {
  return request({
    url: '/pay/mentTransaction',
    method: 'post',
    data: data
  })
}

// 修改支付交易流水信息表
export function updateMentTransaction(data) {
  return request({
    url: '/pay/mentTransaction',
    method: 'put',
    data: data
  })
}

// 删除支付交易流水信息表
export function delMentTransaction(id) {
  return request({
    url: '/pay/mentTransaction/' + id,
    method: 'delete'
  })
}


// 状态启用/停用支付交易流水信息表
export function changeStatus(data) {
  return request({
    url: '/pay/mentTransaction/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出支付交易流水信息表
export function exportMentTransaction(query) {
  return request({
    url: '/pay/mentTransaction/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}