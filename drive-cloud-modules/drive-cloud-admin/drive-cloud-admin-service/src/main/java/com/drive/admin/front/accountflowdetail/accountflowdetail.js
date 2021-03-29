import request from '@/utils/request'

                                
// 分页查询平台账务流水明细列表
export function listPageAccountFlowDetail(query) {
  return request({
    url: '/admin/accountFlowDetail/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询平台账务流水明细列表
export function listAccountFlowDetail(query) {
  return request({
    url: '/admin/accountFlowDetail/findList',
    method: 'get',
    params: query
  })
}

// 查询平台账务流水明细详细
export function getAccountFlowDetail(id) {
  return request({
    url: '/admin/accountFlowDetail/' + id,
    method: 'get'
  })
}

// 新增平台账务流水明细
export function addAccountFlowDetail(data) {
  return request({
    url: '/admin/accountFlowDetail',
    method: 'post',
    data: data
  })
}

// 修改平台账务流水明细
export function updateAccountFlowDetail(data) {
  return request({
    url: '/admin/accountFlowDetail',
    method: 'put',
    data: data
  })
}

// 删除平台账务流水明细
export function delAccountFlowDetail(id) {
  return request({
    url: '/admin/accountFlowDetail/' + id,
    method: 'delete'
  })
}


// 状态启用/停用平台账务流水明细
export function changeStatus(data) {
  return request({
    url: '/admin/accountFlowDetail/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出平台账务流水明细
export function exportAccountFlowDetail(query) {
  return request({
    url: '/admin/accountFlowDetail/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}