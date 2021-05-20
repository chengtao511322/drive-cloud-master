import request from '@/utils/request'

                                    
// 分页查询平台交易流水日志列表
export function listPagePayFlowLog(query) {
  return request({
    url: '/admin/payFlowLog/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询平台交易流水日志列表
export function listPayFlowLog(query) {
  return request({
    url: '/admin/payFlowLog/findList',
    method: 'post',
    data: query
  })
}

// 查询平台交易流水日志详细
export function getPayFlowLog(id) {
  return request({
    url: '/admin/payFlowLog/' + id,
    method: 'get'
  })
}

// 新增平台交易流水日志
export function addPayFlowLog(data) {
  return request({
    url: '/admin/payFlowLog',
    method: 'post',
    data: data
  })
}

// 修改平台交易流水日志
export function updatePayFlowLog(data) {
  return request({
    url: '/admin/payFlowLog',
    method: 'put',
    data: data
  })
}

// 删除平台交易流水日志
export function delPayFlowLog(id) {
  return request({
    url: '/admin/payFlowLog/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台交易流水日志
export function changeStatus(data) {
  return request({
    url: '/admin/payFlowLog/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台交易流水日志
export function exportPayFlowLog(query) {
  return request({
    url: '/admin/payFlowLog/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}